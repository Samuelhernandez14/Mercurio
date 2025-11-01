package com.saviasaludeps.recuperar.objetos;

import com.saviasaludeps.recuperar.generico.ContextManager;
import com.saviasaludeps.recuperar.generico.Log;
import com.saviasaludeps.recuperar.generico.ParamsSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

public class CmFeSoporte {

    private static final String RUTA_SQL = "";

    public static final String PREFIJO_SOPORTE = "soporte_";
    public static final String EXTENSION_ZIP = "ZIP";
    public static final String EXTENSION_PDF = "PDF";
    public static final String EXTENSION_DOC = "DOC";

    private Integer id;
    private FeRipsCarga carga;
    private int tipo;
//    private String extension;
    private Integer maeTipoSoporteId;
    private String maeTipoSoporteCodigo;
    private String maeTipoSoporteValor;
    private String archivoNombre;
    private String archivoRuta;
    private String archivo;
    private String nitFactura;
    private boolean archivoExiste;
    private String usuarioCrea;
    private String terminalCrea;
    private Date fechaHoraCrea;

    public CmFeSoporte() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public FeRipsCarga getCarga() {
        return carga;
    }

    public void setCarga(FeRipsCarga carga) {
        this.carga = carga;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public Integer getMaeTipoSoporteId() {
        return maeTipoSoporteId;
    }

    public void setMaeTipoSoporteId(Integer maeTipoSoporteId) {
        this.maeTipoSoporteId = maeTipoSoporteId;
    }

    public String getMaeTipoSoporteCodigo() {
        return maeTipoSoporteCodigo;
    }

    public void setMaeTipoSoporteCodigo(String maeTipoSoporteCodigo) {
        this.maeTipoSoporteCodigo = maeTipoSoporteCodigo;
    }

    public String getMaeTipoSoporteValor() {
        return maeTipoSoporteValor;
    }

    public void setMaeTipoSoporteValor(String maeTipoSoporteValor) {
        this.maeTipoSoporteValor = maeTipoSoporteValor;
    }

    public String getArchivoNombre() {
        return archivoNombre;
    }

    public void setArchivoNombre(String archivoNombre) {
        this.archivoNombre = archivoNombre;
    }

    public String getArchivoRuta() {
        return archivoRuta;
    }

    public void setArchivoRuta(String archivoRuta) {
        this.archivoRuta = archivoRuta;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String getNitFactura() {
        return nitFactura;
    }

    public void setNitFactura(String nitFactura) {
        this.nitFactura = nitFactura;
    }

    public boolean isArchivoExiste() {
        return archivoExiste;
    }

    public void setArchivoExiste(boolean archivoExiste) {
        this.archivoExiste = archivoExiste;
    }

    public String getUsuarioCrea() {
        return usuarioCrea;
    }

    public void setUsuarioCrea(String usuarioCrea) {
        this.usuarioCrea = usuarioCrea;
    }

    public String getTerminalCrea() {
        return terminalCrea;
    }

    public void setTerminalCrea(String terminalCrea) {
        this.terminalCrea = terminalCrea;
    }

    public Date getFechaHoraCrea() {
        return fechaHoraCrea;
    }

    public void setFechaHoraCrea(Date fechaHoraCrea) {
        this.fechaHoraCrea = fechaHoraCrea;
    }

    public static int crear(CmFeSoporte soporte, Connection conn) {
        PreparedStatement pstm = null;
        try {
            ParamsSQL params = new ParamsSQL();
            params.setInt("cm_fe_rips_cargas_id", soporte.getCarga().getId());
            params.setInt("cm_facturas_id", soporte.getCarga().getFacturaId());
            params.setInt("gn_empresas_id", soporte.getCarga().getEmpresaId());
            params.setInt("mae_tipo_soporte_id", soporte.getMaeTipoSoporteId());
            params.setString("mae_tipo_soporte_codigo", soporte.getMaeTipoSoporteCodigo());
            params.setString("mae_tipo_soporte_valor", soporte.getMaeTipoSoporteValor());
            params.setString("archivo_nombre", soporte.getArchivoNombre());
            params.setString("archivo_ruta", soporte.getArchivoRuta());
            params.setString("archivo", soporte.getArchivo());
            params.setString("usuario_crea", soporte.getUsuarioCrea());
            params.setString("terminal_crea", soporte.getTerminalCrea());
            params.setDateTime("fecha_hora_crea", soporte.getFechaHoraCrea());
            pstm = conn.prepareStatement(ContextManager.getInstance().getQuery(RUTA_SQL + "cm_fe_soporte_crear.sql", params).toString());
            pstm.execute();
        } catch (SQLException e) {
            Log.getInstance().error("Crear Soporte", e.getMessage(), e);
        } finally {
            if (pstm != null) {
                try {
                    pstm.close();
                } catch (SQLException ex) {
                }
            }
        }
        return 0;
    }

}
