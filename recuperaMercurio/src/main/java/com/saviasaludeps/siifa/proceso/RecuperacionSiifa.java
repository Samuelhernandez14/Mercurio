package com.saviasaludeps.siifa.proceso;

import com.saviasaludeps.siifa.generico.ConnectionManagerMySql;
import com.saviasaludeps.siifa.generico.Log;
import com.saviasaludeps.siifa.generico.Nro;
import com.saviasaludeps.siifa.generico.PropApl;
import com.saviasaludeps.siifa.objetos.ConsumoREST;
import com.saviasaludeps.siifa.objetos.ReferenciaCums;
import com.saviasaludeps.siifa.objetos.ReferenciaCumsDAO;
import java.sql.Connection;

public class RecuperacionSiifa {
    
    public static void procesamiento() {
    
        System.out.println("INICIO PROCESO SIIFA - GUARDANDO EN BASE DE DATOS MYSQL");
        Log.getInstance().suceso("Inicio Proceso SIIFA", "Iniciando recuperación de referencias CUMS");
        
        int idInicio = Nro.toInt(PropApl.getInstance().get(PropApl.ID_INICIO));
        int idFin = Nro.toInt(PropApl.getInstance().get(PropApl.ID_FIN));
        int tiempoEspera = Nro.toInt(PropApl.getInstance().get(PropApl.TIEMPO_ESPERA_PETICION));
        
        System.out.println("PARÁMETROS DE CONFIGURACIÓN:");
        System.out.println("  - ID Inicio: " + idInicio);
        System.out.println("  - ID Fin: " + idFin);
        System.out.println("  - Total a procesar: " + (idFin - idInicio + 1));
        System.out.println("  - Tiempo de espera entre peticiones: " + tiempoEspera + " ms");
        System.out.println("  - Base de datos: " + PropApl.getInstance().get(PropApl.BD_MYSQL_NOMBRE));
        System.out.println("  - Servidor: " + PropApl.getInstance().get(PropApl.BD_MYSQL_URL));
        System.out.println();
        
        int totalProcesados = 0;
        int totalExitosos = 0;
        int totalGuardadosDB = 0;
        int totalErroresDB = 0;
        int totalErrores = 0;
        int totalNoEncontrados = 0;
        
        Connection conn = null;
        
        try {
            // Obtener conexión a la base de datos MySQL
            System.out.println("Conectando a MySQL...");
            conn = ConnectionManagerMySql.getInstance().getConnection();
            System.out.println("✓ Conexión establecida a la base de datos MySQL\n");
            
            for (int i = idInicio; i <= idFin; i++) {
                try {
                    System.out.println("-".repeat(80));
                    System.out.println("Consultando ID CUMS: " + i);
                    
                    ReferenciaCums referencia = ConsumoREST.consultarReferenciaCums(i);
                    
                    if (referencia != null) {
                        totalExitosos++;
                        System.out.println("DATOS EXTRAÍDOS EXITOSAMENTE:");
                       
                        
                        // Guardar en base de datos MySQL
                        System.out.println(" Guardando en base de datos ...");
                        int resultado = ReferenciaCumsDAO.crear(referencia, conn);
                        
                        if (resultado == 0) {
                            totalGuardadosDB++;
                            Log.getInstance().suceso("Registro Guardado", 
                                "ID CUMS " + i + " guardado exitosamente en MySQL");
                        } else {
                            totalErroresDB++;
                            Log.getInstance().error("Error BD", 
                                "No se pudo guardar ID CUMS " + i + " en MySQL", null);
                        }
                    } else {
                        totalNoEncontrados++;
                        System.out.println(" No se encontró información para este ID");
                    }
                    
                    totalProcesados++;
                    
                    // Resumen cada 10 registros
                    if (totalProcesados % 10 == 0) {
                        System.out.println("RESUMEN ");
                        System.out.println("  Procesados: " + totalProcesados + "/" + (idFin - idInicio + 1));
                        System.out.println("  Consultados exitosos: " + totalExitosos);
                        System.out.println("  Guardados en BD MySQL: " + totalGuardadosDB);
                        System.out.println("  Errores BD: " + totalErroresDB);
                        System.out.println("  No encontrados: " + totalNoEncontrados);
                        System.out.println("  Errores consulta: " + totalErrores);
                        System.out.println("=".repeat(80) + "\n");
                        
                        Log.getInstance().suceso("Progreso", 
                            String.format("Procesados: %d/%d - Exitosos: %d - Guardados BD: %d - Errores BD: %d", 
                                totalProcesados, (idFin - idInicio + 1), totalExitosos, totalGuardadosDB, totalErroresDB));
                    }
                    
                    // Espera entre peticiones
                    if (tiempoEspera > 0 && i < idFin) {
                        Thread.sleep(tiempoEspera);
                    }
                    
                } catch (Exception ex) {
                    totalErrores++;
                    System.err.println(" ERROR procesando ID " + i + ": " + ex.getMessage());
                    Log.getInstance().error("Error procesando ID " + i, ex.getMessage(), ex);
                }
            }
            
            // Resumen final
            System.out.println("\n" + "=".repeat(80));
            System.out.println(" FIN DEL PROCESO");
            System.out.println("--");
            System.out.println("  Total procesados: " + totalProcesados);
            System.out.println("  Consultados exitosos: " + totalExitosos + " (" + 
                String.format("%.2f", (totalExitosos * 100.0 / totalProcesados)) + "%)");
            System.out.println("  Guardados en BD MySQL: " + totalGuardadosDB + " (" + 
                String.format("%.2f", (totalGuardadosDB * 100.0 / totalProcesados)) + "%)");
            System.out.println("  Errores BD: " + totalErroresDB);
            System.out.println("  No encontrados: " + totalNoEncontrados + " (" + 
                String.format("%.2f", (totalNoEncontrados * 100.0 / totalProcesados)) + "%)");
            System.out.println("  Errores consulta: " + totalErrores + " (" + 
                String.format("%.2f", (totalErrores * 100.0 / totalProcesados)) + "%)");
            System.out.println("=".repeat(80));
            
            Log.getInstance().suceso("Fin Proceso SIIFA", 
                String.format("Total: %d - Exitosos: %d - Guardados BD: %d - Errores BD: %d", 
                    totalProcesados, totalExitosos, totalGuardadosDB, totalErroresDB));
                    
        } catch (Exception ex) {
            System.err.println("ERROR CRÍTICO EN EL PROCESO: " + ex.getMessage());
            ex.printStackTrace();
            Log.getInstance().error("Error Proceso SIIFA", ex.getMessage(), ex);
        } finally {
            // Cerrar conexión
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("Conexión a base de datos MySQL cerrada");
                } catch (Exception e) {
                    System.err.println("Error cerrando conexión: " + e.getMessage());
                }
            }
        }
        
        System.out.println("PROCESO FINALIZADO");
    }
}