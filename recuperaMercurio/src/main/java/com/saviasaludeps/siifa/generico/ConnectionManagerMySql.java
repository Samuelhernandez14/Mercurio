package com.saviasaludeps.siifa.generico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Esta clase permite el acceso a uno o varios pools de conexion, que son
 * definidos en un archivo de Propiedades (Pool.properties). Un cliente puede
 * conectarse al pool a traés del metodo getInstance(), lo cual le da la
 * posibilidad de registrar y comprobar conexiones del pool. Para desconectarse
 * completamente, el cliente debe llamar al metodo release() para cerrar todas
 * las conexiones abiertas y dejar el pool "limpio".
 */
public class ConnectionManagerMySql {

    private static ConnectionManagerMySql instance = null;
    private static String url = "";
    private static String name = "";
    private static String usr = "";
    private static String pass = "";

    /**
     * Retorna una instancia de conexion, creandola si es la primera vez que el
     * metodo es llamado.
     *
     * @return DBConnectionManager La instancia.
     * @throws java.lang.Exception
     */
    public static ConnectionManagerMySql getInstance() throws Exception/* throws SQLException*/ {
        if (instance == null) {

        }
        return instance;
    }

    /**
     * Carga el archivo de propiedades Pool.properties e inicializa las
     * instancias con dichos valores.
     */
    private ConnectionManagerMySql() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            throw e;
        }
    }
//    private ConnectionManagerMySql() throws Exception {
//        try {
//            Class.forName("com.mysql.jdbc.Driver").newInstance();
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
//            throw e;
//        }
//    }

    /**
     * Retorna una conexion abierta. Si no hay conexion disponible, y no se ha
     * alcanzado el numero maximo de conexiones, se crea una nueva conexion.
     *
     * @return Connection Nombre de la conexion
     * @throws java.sql.SQLException
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url + "/" + name, usr, pass);
    }
}
