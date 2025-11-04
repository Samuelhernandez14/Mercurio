package com.saviasaludeps.siifa.generico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Esta clase permite el acceso a conexiones MySQL
 */
public class ConnectionManagerMySql {

    private static ConnectionManagerMySql instance = null;
    private static String url = "";
    private static String name = "";
    private static String usr = "";
    private static String pass = "";

    /**
     * Retorna una instancia de conexión, creándola si es la primera vez que el
     * método es llamado.
     */
    public static ConnectionManagerMySql getInstance() throws Exception {
        if (instance == null) {
            instance = new ConnectionManagerMySql();
            
            // Cargar configuración desde PropApl
            url = PropApl.getInstance().get(PropApl.BD_MYSQL_URL);
            name = PropApl.getInstance().get(PropApl.BD_MYSQL_NOMBRE);
            usr = PropApl.getInstance().get(PropApl.BD_MYSQL_USUARIO);
            pass = PropApl.getInstance().get(PropApl.BD_MYSQL_PASSWORD);
            
            System.out.println("✓ Inicializando conexión a MySQL");
            System.out.println("  URL: " + url);
            System.out.println("  Base de datos: " + name);
            System.out.println("  Usuario: " + usr);
        }
        return instance;
    }

    /**
     * Constructor privado - carga el driver MySQL
     */
    private ConnectionManagerMySql() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            System.out.println("✓ Driver MySQL cargado exitosamente");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            System.err.println("✗ Error cargando driver MySQL: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Retorna una conexión abierta. Si no hay conexión disponible, crea una nueva.
     */
    public Connection getConnection() throws SQLException {
        try {
            Connection conn = DriverManager.getConnection(url + "/" + name + "?useSSL=false&serverTimezone=UTC", usr, pass);
            System.out.println("✓ Conexión a MySQL establecida");
            return conn;
        } catch (SQLException e) {
            System.err.println("✗ Error conectando a MySQL: " + e.getMessage());
            throw e;
        }
    }
}