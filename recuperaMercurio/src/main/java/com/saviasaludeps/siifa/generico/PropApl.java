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
        dbProps = new Properties();
        InputStream is = null;
        
        try {
            // Intenta varias rutas posibles
            String[] rutasPosibles = {
                "/com/saviasaludeps/siifa/config/apl.properties",
                "/config/apl.properties",
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
                System.err.println("\nVerifica que el archivo esté en:");
                System.err.println("  src/main/resources/com/saviasaludeps/siifa/config/apl.properties");
                System.err.println("  o en: src/main/resources/config/apl.properties");
                System.err.println("  o en: src/main/resources/apl.properties");
                
                // Usar valores por defecto para poder continuar
                cargarValoresPorDefecto();
                return;
            }
            
            dbProps.load(is);
            System.out.println("✓ Propiedades cargadas exitosamente");
            
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
    
    /**
     * Carga valores por defecto si no se encuentra el archivo
     */
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
        dbProps.setProperty(TIEMPO_ESPERA_PETICION, "100");
        dbProps.setProperty(TOKEN_TIEMPO_VALIDEZ, "3600");
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