package com.saviasaludeps.siifa.objetos;

import com.saviasaludeps.siifa.generico.ConnectionManagerMySql;
import com.saviasaludeps.siifa.generico.ContextManager;
import com.saviasaludeps.siifa.generico.ParamsSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Sincronizacion {

    private static final String RUTA_SQL = "";

    public static final int ESTADO_EN_PROCESO = 0;
    public static final int ESTADO_FINALIZADO = 1;
    public static final int ESTADO_ERROR = 2;

    private int id;
    private int idInicio;
    private int idFin;
    private Date fechaHoraInicio;
    private Date fechaHoraFin;
    private int duracion;
    private int estado;
    private String descripcion;
    private int registrosConsultados;
    private int registrosExitosos;
    private int registrosError;

    public Sincronizacion() {
    }

    public Sincronizacion(int idInicio, int idFin, String descripcion) {
        this.idInicio = idInicio;
        this.idFin = idFin;
        this.fechaHoraInicio = new Date();
        this.estado = ESTADO_EN_PROCESO;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdInicio() {
        return idInicio;
    }

    public void setIdInicio(int idInicio) {
        this.idInicio = idInicio;
    }

    public int getIdFin() {
        return idFin;
    }

    public void setIdFin(int idFin) {
        this.idFin = idFin;
    }

    public Date getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(Date fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public Date getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(Date fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getRegistrosConsultados() {
        return registrosConsultados;
    }

    public void setRegistrosConsultados(int registrosConsultados) {
        this.registrosConsultados = registrosConsultados;
    }

    public int getRegistrosExitosos() {
        return registrosExitosos;
    }

    public void setRegistrosExitosos(int registrosExitosos) {
        this.registrosExitosos = registrosExitosos;
    }

    public int getRegistrosError() {
        return registrosError;
    }

    public void setRegistrosError(int registrosError) {
        this.registrosError = registrosError;
    }

    public void incrementarConsultados() {
        this.registrosConsultados++;
    }

    public void incrementarExitosos() {
        this.registrosExitosos++;
    }

    public void incrementarError() {
        this.registrosError++;
    }

    /**
     * Consulta el último ID procesado exitosamente
     */
    public static Integer consultarUltimoIdProcesado(Connection conn) throws Exception {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Integer ultimoId = null;
        
        try {
            pstmt = conn.prepareStatement(
                ContextManager.getInstance()
                    .getQuery(RUTA_SQL + "sincronizacion_consultar_ultimo_id.sql")
                    .toString()
            );
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                ultimoId = rs.getInt("id_fin");
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
        
        return ultimoId;
    }

    /**
     * Crea un nuevo registro de sincronización
     */
    public static int crear(Sincronizacion obj, Connection conn) throws SQLException, Exception {
        int idResp = 0;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            ParamsSQL params = new ParamsSQL();
            params.setInt("id_inicio", obj.getIdInicio());
            params.setInt("id_fin", obj.getIdFin());
            params.setDateTime("fecha_hora_inicio", obj.getFechaHoraInicio());
            params.setInt("estado", obj.getEstado());
            params.setString("descripcion", obj.getDescripcion());
            
            pstmt = conn.prepareStatement(
                ContextManager.getInstance()
                    .getQuery(RUTA_SQL + "sincronizacion_crear.sql", params)
                    .toString(),
                Statement.RETURN_GENERATED_KEYS
            );
            pstmt.execute();
            
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                idResp = rs.getInt(1);
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
        
        return idResp;
    }

    /**
     * Finaliza una sincronización
     */
    public static void finalizar(Sincronizacion obj, Connection conn) throws SQLException, Exception {
        PreparedStatement pstmt = null;
        
        try {
            ParamsSQL params = new ParamsSQL();
            params.setDateTime("fecha_hora_fin", new Date());
            params.setInt("duracion", obj.getDuracion());
            params.setInt("estado", obj.getEstado());
            params.setString("descripcion", obj.getDescripcion());
            params.setInt("registros_consultados", obj.getRegistrosConsultados());
            params.setInt("registros_exitosos", obj.getRegistrosExitosos());
            params.setInt("registros_error", obj.getRegistrosError());
            params.setInt("id_pre", obj.getId());
            
            pstmt = conn.prepareStatement(
                ContextManager.getInstance()
                    .getQuery(RUTA_SQL + "sincronizacion_finalizar.sql", params)
                    .toString()
            );
            pstmt.execute();
        } finally {
            if (pstmt != null) pstmt.close();
        }
    }
}