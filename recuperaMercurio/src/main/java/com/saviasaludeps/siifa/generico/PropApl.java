package com.saviasaludeps.siifa.generico;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropApl {

    public static final String RUTA_LOGS = "ruta_logs";
    public static final String URL_REST_SIIFA = "url_rest_siifa";
    public static final String URL_REST_SIIFA_TOKEN = "url_rest_siifa_token";
    public static final String SIIFA_USERNAME = "siifa_username";
    public static final String SIIFA_PASSWORD = "siifa_password";
    public static final String ID_INICIO = "id_inicio";
    public static final String ID_FIN = "id_fin";
    public static final String LOTE_SIZE = "lote_size";
    public static final String TIEMPO_ESPERA_PETICION = "tiempo_espera_peticion";
    public static final String TOKEN_TIEMPO_VALIDEZ = "token_tiempo_validez";

    public static final String BD_MYSQL_USUARIO = "mysqlDbUser";
    public static final String BD_MYSQL_PASSWORD = "mysqlDbPassword";
    public static final String BD_MYSQL_NOMBRE = "mysqlDdName";
    public static final String BD_MYSQL_URL = "mysqlDbURL";

    private static PropApl propAplInstance = null;
    private Properties dbProps = null;

    private PropApl() {
        InputStream is = getClass().getResourceAsStream("/com/saviasaludeps/siifa/config/apl.properties");
        dbProps = new Properties();
        try {
            dbProps.load(is);
        } catch (IOException e) {
            Log.getInstance().error("Carga de Propiedades", 
                "No se puede leer el archivo de propiedades apl.properties", e);
        }
    }

    public static PropApl getInstance() {
        if (propAplInstance == null) {
            propAplInstance = new PropApl();
        }
        return propAplInstance;
    }

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
    
    public int getInt(String param) {
        String value = get(param);
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}