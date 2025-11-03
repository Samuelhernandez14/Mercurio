package com.saviasaludeps.siifa.proceso;

import com.saviasaludeps.siifa.generico.Log;
import com.saviasaludeps.siifa.generico.Nro;
import com.saviasaludeps.siifa.generico.PropApl;
import com.saviasaludeps.siifa.objetos.ConsumoREST;
import com.saviasaludeps.siifa.objetos.ReferenciaCums;

public class RecuperacionSiifa {
    
    public static void procesamiento() {
        System.out.println("=".repeat(80));
        System.out.println("INICIO PROCESO SIIFA - MODO PRUEBA (SIN BASE DE DATOS)");
        System.out.println("=".repeat(80));
        
        Log.getInstance().suceso("Inicio Proceso SIIFA", "Iniciando recuperación de referencias CUMS - MODO PRUEBA");
        
        int idInicio = Nro.toInt(PropApl.getInstance().get(PropApl.ID_INICIO));
        int idFin = Nro.toInt(PropApl.getInstance().get(PropApl.ID_FIN));
        int tiempoEspera = Nro.toInt(PropApl.getInstance().get(PropApl.TIEMPO_ESPERA_PETICION));
        
        System.out.println("\nPARÁMETROS DE CONFIGURACIÓN:");
        System.out.println("  - ID Inicio: " + idInicio);
        System.out.println("  - ID Fin: " + idFin);
        System.out.println("  - Total a procesar: " + (idFin - idInicio + 1));
        System.out.println("  - Tiempo de espera entre peticiones: " + tiempoEspera + " ms");
        System.out.println();
        
        int totalProcesados = 0;
        int totalExitosos = 0;
        int totalErrores = 0;
        int totalNoEncontrados = 0;
        
        try {
            for (int i = idInicio; i <= idFin; i++) {
                try {
                    System.out.println("-".repeat(80));
                    System.out.println("Consultando ID CUMS: " + i);
                    
                    ReferenciaCums referencia = ConsumoREST.consultarReferenciaCums(i);
                    
                    if (referencia != null) {
                        totalExitosos++;
                        System.out.println("✓ DATOS EXTRAÍDOS EXITOSAMENTE:");
                        System.out.println("  • ID CUMS: " + referencia.getIdCums());
                        System.out.println("  • Expediente CUM: " + referencia.getExpedienteCum());
                        System.out.println("  • Consecutivo CUM: " + referencia.getConsecutivoCum());
                        System.out.println("  • Nombre: " + referencia.getNombre());
                        System.out.println("  • Descripción: " + (referencia.getDescripcion() != null ? 
                            (referencia.getDescripcion().length() > 100 ? 
                                referencia.getDescripcion().substring(0, 100) + "..." : 
                                referencia.getDescripcion()) : "N/A"));
                        System.out.println("  • Código ATC: " + referencia.getCodigoAtc());
                        System.out.println("  • Incluido PBS: " + referencia.getIncluidoPbs());
                    } else {
                        totalNoEncontrados++;
                        System.out.println("⚠ No se encontró información para este ID");
                    }
                    
                    totalProcesados++;
                    
                    // Resumen cada 10 registros
                    if (totalProcesados % 10 == 0) {
                        System.out.println("\n" + "=".repeat(80));
                        System.out.println("RESUMEN PARCIAL:");
                        System.out.println("  Procesados: " + totalProcesados + "/" + (idFin - idInicio + 1));
                        System.out.println("  Exitosos: " + totalExitosos);
                        System.out.println("  No encontrados: " + totalNoEncontrados);
                        System.out.println("  Errores: " + totalErrores);
                        System.out.println("=".repeat(80) + "\n");
                        
                        Log.getInstance().suceso("Progreso", 
                            String.format("Procesados: %d/%d - Exitosos: %d - No encontrados: %d - Errores: %d", 
                                totalProcesados, (idFin - idInicio + 1), totalExitosos, totalNoEncontrados, totalErrores));
                    }
                    
                    // Espera entre peticiones
                    if (tiempoEspera > 0 && i < idFin) {
                        Thread.sleep(tiempoEspera);
                    }
                    
                } catch (Exception ex) {
                    totalErrores++;
                    System.err.println("✗ ERROR procesando ID " + i + ": " + ex.getMessage());
                    Log.getInstance().error("Error procesando ID " + i, ex.getMessage(), ex);
                }
            }
            
            // Resumen final
            System.out.println("\n" + "=".repeat(80));
            System.out.println("RESUMEN FINAL DEL PROCESO");
            System.out.println("=".repeat(80));
            System.out.println("  Total procesados: " + totalProcesados);
            System.out.println("  Exitosos: " + totalExitosos + " (" + 
                String.format("%.2f", (totalExitosos * 100.0 / totalProcesados)) + "%)");
            System.out.println("  No encontrados: " + totalNoEncontrados + " (" + 
                String.format("%.2f", (totalNoEncontrados * 100.0 / totalProcesados)) + "%)");
            System.out.println("  Errores: " + totalErrores + " (" + 
                String.format("%.2f", (totalErrores * 100.0 / totalProcesados)) + "%)");
            System.out.println("=".repeat(80));
            
            Log.getInstance().suceso("Fin Proceso SIIFA", 
                String.format("Total procesados: %d - Exitosos: %d - No encontrados: %d - Errores: %d", 
                    totalProcesados, totalExitosos, totalNoEncontrados, totalErrores));
                    
        } catch (Exception ex) {
            System.err.println("\n✗ ERROR CRÍTICO EN EL PROCESO: " + ex.getMessage());
            ex.printStackTrace();
            Log.getInstance().error("Error Proceso SIIFA", ex.getMessage(), ex);
        }
        
        System.out.println("\nPROCESO FINALIZADO");
    }
}