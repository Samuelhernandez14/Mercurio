package com.saviasaludeps.siifa.objetos;

import com.saviasaludeps.siifa.generico.Log;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReferenciaCumsDAO {
    
    public static int crear(ReferenciaCums referencia, Connection conn) {
        PreparedStatement pstm = null;
        try {
            // Query SQL embebido directamente en el código
            String query = "INSERT INTO cums (" +
                    "idCums, expedienteCum, consecutivoCum, nombre, descripcion, codigoAtc, incluidoPbs" +
                    ") VALUES (?, ?, ?, ?, ?, ?, ?) " +
                    "ON DUPLICATE KEY UPDATE " +
                    "expedienteCum = VALUES(expedienteCum), " +
                    "consecutivoCum = VALUES(consecutivoCum), " +
                    "nombre = VALUES(nombre), " +
                    "descripcion = VALUES(descripcion), " +
                    "codigoAtc = VALUES(codigoAtc), " +
                    "incluidoPbs = VALUES(incluidoPbs)";
            
            pstm = conn.prepareStatement(query);
            
            // Establecer los parámetros
            pstm.setInt(1, referencia.getIdCums());
            pstm.setInt(2, referencia.getExpedienteCum() != null ? referencia.getExpedienteCum() : 0);
            pstm.setInt(3, referencia.getConsecutivoCum() != null ? referencia.getConsecutivoCum() : 0);
            pstm.setString(4, referencia.getNombre());
            
            // Truncar descripción si es muy larga (VARCHAR(500))
            String descripcion = referencia.getDescripcion();
            if (descripcion != null && descripcion.length() > 500) {
                descripcion = descripcion.substring(0, 500);
            }
            pstm.setString(5, descripcion);
            
            // Truncar código ATC si es muy largo (VARCHAR(20))
            String codigoAtc = referencia.getCodigoAtc();
            if (codigoAtc != null && codigoAtc.length() > 20) {
                codigoAtc = codigoAtc.substring(0, 20);
            }
            pstm.setString(6, codigoAtc);
            
            pstm.setBoolean(7, referencia.getIncluidoPbs() != null ? referencia.getIncluidoPbs() : false);
            
            // Ejecutar la consulta
            pstm.executeUpdate();
            
            System.out.println("  ✓ Registro guardado en BD - ID CUMS: " + referencia.getIdCums());
            return 0;
            
        } catch (SQLException e) {
            System.err.println("  ✗ Error guardando en BD - ID CUMS: " + referencia.getIdCums() + " - " + e.getMessage());
            Log.getInstance().error("Crear Referencia CUMS", e.getMessage(), e);
            return -1;
        } finally {
            if (pstm != null) {
                try {
                    pstm.close();
                } catch (SQLException ex) {
                    // Ignorar
                }
            }
        }
    }
}