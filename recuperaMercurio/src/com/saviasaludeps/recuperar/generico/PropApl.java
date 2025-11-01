/*
 * PropApl.java
 *
 * Created on 11 de Septiembre de 2015
 */
package com.saviasaludeps.recuperar.generico;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author rpalacic
 *
 */
public class PropApl {

    public static final String RUTA_LOGS = "ruta_logs";
    public static final String RUTA_ALMACENAMIENTO = "ruta_almacenamiento";
    public static final String URL_WS_SOAP_ANEXOS = "url_ws_soap_anexos";
    public static final String URL_WS_SOAP_ADJUNTOS = "url_ws_soap_adjuntos";

    public static final String BD_MYSQL_USUARIO = "mysqlDbUser";
    public static final String BD_MYSQL_PASSWORD = "mysqlDbPassword";
    public static final String BD_MYSQL_NOMBRE = "mysqlDdName";
    public static final String BD_MYSQL_URL = "mysqlDbURL";

    private static PropApl propAplInstance = null;
    private Properties dbProps = null;

    private PropApl() {
        InputStream is = getClass().getResourceAsStream("/com/saviasaludeps/recuperar/config/apl.properties");
        dbProps = new Properties();
        try {
            dbProps.load(is);
        } catch (IOException e) {
            Log.getInstance().error("Carga de Propiedades", "No se puede leer el archivo de propiedades apl.properties", e);
        }
    }

    public static PropApl getInstance() {
        if (propAplInstance == null) {
            propAplInstance = new PropApl();
        }
        return propAplInstance;
    }

    /**
     * Obtiene el valor de un parámetro
     *
     * @param param
     * @return
     */
    public String get(String param) {
        if (dbProps == null) {
            return "";
        }
        String str = dbProps.getProperty(param);
        if (str == null) {
            str = "";
        }
        return str;
    }

}
