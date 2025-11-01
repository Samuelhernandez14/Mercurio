package com.saviasaludeps.recuperar.objetos;

import com.saviasaludeps.recuperar.generico.ContextManager;
import com.saviasaludeps.recuperar.generico.Log;
import com.saviasaludeps.recuperar.generico.ParamsSQL;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FeRipsCarga implements Serializable {

    private static final String RUTA_SQL = "";

    public static final int SOPORTE_MERCURIO_PENDIENTE = 0;
    public static final int SOPORTE_MERCURIO_SINCRONIZADO = 1;

    private Integer id;
    private int tipo;
    private int facturaId;
    private String notaNumero;
    private int empresaId;
    private String empresaNit;
    private int estado;
    private int soportesMercurio;

    public FeRipsCarga() {

    }

    public FeRipsCarga(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getFacturaId() {
        return facturaId;
    }

    public void setFacturaId(int facturaId) {
        this.facturaId = facturaId;
    }

    public String getNotaNumero() {
        return notaNumero;
    }

    public void setNotaNumero(String notaNumero) {
        this.notaNumero = notaNumero;
    }

    public int getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(int empresaId) {
        this.empresaId = empresaId;
    }

    public String getEmpresaNit() {
        return empresaNit;
    }

    public void setEmpresaNit(String empresaNit) {
        this.empresaNit = empresaNit;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getSoportesMercurio() {
        return soportesMercurio;
    }

    public void setSoportesMercurio(int soportesMercurio) {
        this.soportesMercurio = soportesMercurio;
    }

    public static List<FeRipsCarga> consultarListaPendientesPorFecha(Connection conn, Date fecha) {
        List<FeRipsCarga> lista = new ArrayList();
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try {
            ParamsSQL params = new ParamsSQL();
            params.setDate("fecha_pre", fecha);
            params.setInt("soporte_mercurio_pre", SOPORTE_MERCURIO_PENDIENTE);
            pstm = conn.prepareStatement(ContextManager.getInstance().getQuery(RUTA_SQL + "fe_rips_cargas_consultar.sql", params).toString());
            rs = pstm.executeQuery();
            FeRipsCarga anexo;
            while (rs.next()) {
                try {
                    anexo = new FeRipsCarga();
                    anexo.setId(rs.getInt("id"));
                    anexo.setTipo(rs.getInt("tipo"));
                    anexo.setFacturaId(rs.getInt("factura_id"));
                    anexo.setNotaNumero(rs.getString("numero_nota"));
                    anexo.setEstado(rs.getInt("estado"));
                    anexo.setSoportesMercurio(rs.getInt("soporte_mercurio"));
                    anexo.setEmpresaId(rs.getInt("empresa_id"));
                    anexo.setEmpresaNit(rs.getString("empresa_nit"));
                    lista.add(anexo);
                } catch (SQLException e) {

                } catch (Exception e) {

                }
            }
        } catch (SQLException e) {
            Log.getInstance().error("Consulta de Anexos 3", e.getMessage(), e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                }
            }
            if (pstm != null) {
                try {
                    pstm.close();
                } catch (SQLException ex) {
                }
            }
        }
        return lista;
    }

}
