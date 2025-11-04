package com.saviasaludeps.siifa.generico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Gestor de conexiones para SQL Server
 */
public class ConnectionManagerSqlServer {

    private static ConnectionManagerSqlServer instance = null;
    private static String url = "";

    /**
     * Retorna una instancia de conexión
     */
    public static ConnectionManagerSqlServer getInstance() throws Exception {
        if (instance == null) {
            instance = new ConnectionManagerSqlServer();
            url = PropApl.getInstance().get(PropApl.BD_SQLSERVER_URL);
            
            System.out.println("✓ Inicializando conexión a SQL Server");
            System.out.println("  URL: " + url);
        }
        return instance;
    }

    /**
     * Constructor privado - carga el driver de SQL Server
     */
    private ConnectionManagerSqlServer() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        try {
            // Driver de SQL Server (Microsoft JDBC Driver)
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("✓ Driver SQL Server cargado exitosamente");
        } catch (ClassNotFoundException e) {
            System.err.println("✗ Error cargando driver SQL Server: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Retorna una conexión abierta a SQL Server
     */
    public Connection getConnection() throws SQLException {
        try {
            Connection conn = DriverManager.getConnection(url);
            System.out.println("✓ Conexión a SQL Server establecida");
            return conn;
        } catch (SQLException e) {
            System.err.println("✗ Error conectando a SQL Server: " + e.getMessage());
            throw e;
        }
    }
}