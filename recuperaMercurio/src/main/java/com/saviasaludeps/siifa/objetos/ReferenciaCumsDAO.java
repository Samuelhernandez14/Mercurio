package com.saviasaludeps.siifa.objetos;

import com.saviasaludeps.siifa.generico.ContextManager;
import com.saviasaludeps.siifa.generico.Log;
import com.saviasaludeps.siifa.generico.ParamsSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReferenciaCumsDAO {
    
    private static final String RUTA_SQL = "";
    
    public static int crear(ReferenciaCums referencia, Connection conn) {
        PreparedStatement pstm = null;
        try {
            ParamsSQL params = new ParamsSQL();
            params.setInt("id_cums", referencia.getIdCums());
            params.setInt("expediente_cum", referencia.getExpedienteCum());
            params.setInt("consecutivo_cum", referencia.getConsecutivoCum());
            params.setString("nombre", referencia.getNombre());
            params.setString("descripcion", referencia.getDescripcion());
            params.setString("codigo_atc", referencia.getCodigoAtc());
            params.setBoolean("incluido_pbs", referencia.getIncluidoPbs());
            
            pstm = conn.prepareStatement(ContextManager.getInstance()
                    .getQuery(RUTA_SQL + "referencia_cums_crear.sql", params).toString());
            pstm.execute();
            return 0;
        } catch (SQLException e) {
            Log.getInstance().error("Crear Referencia CUMS", e.getMessage(), e);
            return -1;
        } finally {
            if (pstm != null) {
                try {
                    pstm.close();
                } catch (SQLException ex) {
                }
            }
        }
    }
}