package com.saviasaludeps.siifa.proceso;

import com.saviasaludeps.siifa.generico.ConnectionManagerMySql;
import com.saviasaludeps.siifa.generico.Log;
import com.saviasaludeps.siifa.generico.Nro;
import com.saviasaludeps.siifa.generico.PropApl;
import com.saviasaludeps.siifa.objetos.ConsumoREST;
import com.saviasaludeps.siifa.objetos.ReferenciaCums;
import com.saviasaludeps.siifa.objetos.ReferenciaCumsDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class RecuperacionSiifa {
    
    public static void procesamiento() {
        Log.getInstance().suceso("Inicio Proceso SIIFA", "Iniciando recuperación de referencias CUMS");
        
        Connection connMySql = null;
        int idInicio = Nro.toInt(PropApl.getInstance().get(PropApl.ID_INICIO));
        int idFin = Nro.toInt(PropApl.getInstance().get(PropApl.ID_FIN));
        int tiempoEspera = Nro.toInt(PropApl.getInstance().get(PropApl.TIEMPO_ESPERA_PETICION));
        
        int totalProcesados = 0;
        int totalExitosos = 0;
        int totalErrores = 0;
        
        try {
            connMySql = ConnectionManagerMySql.getInstance().getConnection();
            
            for (int i = idInicio; i <= idFin; i++) {
                try {
                    ReferenciaCums referencia = ConsumoREST.consultarReferenciaCums(i);
                    
                    if (referencia != null) {
                        int resultado = ReferenciaCumsDAO.crear(referencia, connMySql);
                        if (resultado == 0) {
                            totalExitosos++;
                        } else {
                            totalErrores++;
                        }
                    }
                    
                    totalProcesados++;
                    
                    // Log cada 100 registros
                    if (totalProcesados % 100 == 0) {
                        Log.getInstance().suceso("Progreso", 
                            String.format("Procesados: %d/%d - Exitosos: %d - Errores: %d", 
                                totalProcesados, (idFin - idInicio + 1), totalExitosos, totalErrores));
                    }
                    
                    // Espera entre peticiones
                    if (tiempoEspera > 0) {
                        Thread.sleep(tiempoEspera);
                    }
                    
                } catch (Exception ex) {
                    totalErrores++;
                    Log.getInstance().error("Error procesando ID " + i, ex.getMessage(), ex);
                }
            }
            
            Log.getInstance().suceso("Fin Proceso SIIFA", 
                String.format("Total procesados: %d - Exitosos: %d - Errores: %d", 
                    totalProcesados, totalExitosos, totalErrores));
                    
        } catch (Exception ex) {
            Log.getInstance().error("Error Proceso SIIFA", ex.getMessage(), ex);
        } finally {
            if (connMySql != null) {
                try {
                    connMySql.close();
                } catch (SQLException ex) {
                    Log.getInstance().error("Error cerrando conexión", ex.getMessage(), ex);
                }
            }
        }
    }
}