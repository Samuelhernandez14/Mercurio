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

    // Propiedades MySQL (mantener por compatibilidad)
    public static final String BD_MYSQL_USUARIO = "mysqlDbUser";
    public static final String BD_MYSQL_PASSWORD = "mysqlDbPassword";
    public static final String BD_MYSQL_NOMBRE = "mysqlDdName";
    public static final String BD_MYSQL_URL = "mysqlDbURL";

    // Propiedades SQL Server
    public static final String BD_SQLSERVER_USUARIO = "sqlServerDbUser";
    public static final String BD_SQLSERVER_PASSWORD = "sqlServerDbPassword";
    public static final String BD_SQLSERVER_NOMBRE = "sqlServerDbName";
    public static final String BD_SQLSERVER_HOST = "sqlServerDbHost";
    public static final String BD_SQLSERVER_PORT = "sqlServerDbPort";
    public static final String BD_SQLSERVER_URL = "sqlServerDbURL";

    private static PropApl propAplInstance = null;
    private Properties dbProps = null;

    private PropApl() {
        dbProps = new Properties();
        InputStream is = null;
        
        try {
            String[] rutasPosibles = {
                "/config/apl.properties",
                "/com/saviasaludeps/siifa/config/apl.properties",
                "/apl.properties"
            };
            
            for (String ruta : rutasPosibles) {
                is = getClass().getResourceAsStream(ruta);
                if (is != null) {
                    System.out.println("✓ Archivo de propiedades encontrado en: " + ruta);
                    break;
                }
            }
            
            if (is == null) {
                System.err.println("✗ ERROR: No se encontró el archivo apl.properties");
                System.err.println("Rutas buscadas:");
                for (String ruta : rutasPosibles) {
                    System.err.println("  - " + ruta);
                }
                cargarValoresPorDefecto();
                return;
            }
            
            dbProps.load(is);
            System.out.println("✓ Propiedades cargadas exitosamente");
            
            // Mostrar propiedades cargadas (sin mostrar passwords)
            System.out.println("\nPropiedades configuradas:");
            System.out.println("  - URL SIIFA: " + get(URL_REST_SIIFA));
            System.out.println("  - Usuario SIIFA: " + get(SIIFA_USERNAME));
            System.out.println("  - URL Base de Datos: " + get(BD_SQLSERVER_URL));
            System.out.println("  - Ruta Logs: " + get(RUTA_LOGS));
            
        } catch (IOException e) {
            System.err.println("✗ Error al cargar el archivo de propiedades: " + e.getMessage());
            cargarValoresPorDefecto();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    // Ignorar
                }
            }
        }
    }
    
    private void cargarValoresPorDefecto() {
        System.out.println("⚠ Usando valores por defecto...");
        dbProps.setProperty(RUTA_LOGS, "logs/");
        dbProps.setProperty(URL_REST_SIIFA, "https://siifa.sispropreprod.gov.co/siifacon/api/ReferenciaCums/ByIdCums");
        dbProps.setProperty(URL_REST_SIIFA_TOKEN, "https://siifa.sispropreprod.gov.co/siifaseg/api/Auth/login");
        dbProps.setProperty(SIIFA_USERNAME, "CC71790949");
        dbProps.setProperty(SIIFA_PASSWORD, "Savia.2025@2");
        dbProps.setProperty(ID_INICIO, "1");
        dbProps.setProperty(ID_FIN, "10");
        dbProps.setProperty(LOTE_SIZE, "100");
        dbProps.setProperty(TIEMPO_ESPERA_PETICION, "500");
        
        // SQL Server por defecto
        dbProps.setProperty(BD_SQLSERVER_URL, "jdbc:sqlserver://localhost:1433;databaseName=prueba1;integratedSecurity=true;trustServerCertificate=true");
        
        // MySQL por defecto (por compatibilidad)
        dbProps.setProperty(BD_MYSQL_URL, "jdbc:mysql://localhost:3306");
        dbProps.setProperty(BD_MYSQL_NOMBRE, "");
        dbProps.setProperty(BD_MYSQL_USUARIO, "");
        dbProps.setProperty(BD_MYSQL_PASSWORD, "");
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