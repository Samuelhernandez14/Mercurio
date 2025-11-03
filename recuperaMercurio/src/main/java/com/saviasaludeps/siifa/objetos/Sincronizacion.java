package com.saviasaludeps.siifa.objetos;

import com.saviasaludeps.siifa.generico.ConnectionManagerMySql;
import com.saviasaludeps.siifa.generico.ContextManager;
import com.saviasaludeps.siifa.generico.ParamsSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Sincronizacion {

    private static final String RUTA_SQL = "";

    public static final int ESTADO_EN_PROCESO = 0;
    public static final int ESTADO_FINALIZADO = 1;
    public static final int ESTADO_ERROR = 2;

    private int id;
    private Date fechaHoraInicio;
    private Date fechaHoraFin;
    private int duracion;
    private int estado;
    private String descripcion;
    private int registrosConsultados;
    private int registrosActualizados;
    private int registrosErrores;

    public Sincronizacion() {
        this.fechaHoraInicio = new Date();
        this.estado = ESTADO_EN_PROCESO;
    }

    public Sincronizacion(String descripcion) {
        this();
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getDuracionCalculado() {
        if (fechaHoraInicio != null && fechaHoraFin != null) {
            Duration duration = Duration.between(
                fechaHoraInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(),
                fechaHoraFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()
            );
            return (int) duration.getSeconds();
        }
        return 0;
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

    public int getRegistrosActualizados() {
        return registrosActualizados;
    }

    public void setRegistrosActualizados(int registrosActualizados) {
        this.registrosActualizados = registrosActualizados;
    }

    public int getRegistrosErrores() {
        return registrosErrores;
    }

    public void setRegistrosErrores(int registrosErrores) {
        this.registrosErrores = registrosErrores;
    }

    // Métodos de incremento
    public void incrementarConsultados() {
        this.registrosConsultados++;
    }

    public void incrementarActualizados() {
        this.registrosActualizados++;
    }

    public void incrementarErrores() {
        this.registrosErrores++;
    }

    // Métodos de BD
    public static int crear(Sincronizacion obj, Connection conn) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            ParamsSQL params = new ParamsSQL();
            params.setDateTime("fecha_hora_inicio", obj.getFechaHoraInicio());
            params.setInt("estado", obj.getEstado());
            params.setString("descripcion", obj.getDescripcion());
            
            pstmt = conn.prepareStatement(
                ContextManager.getInstance().getQuery(RUTA_SQL + "sincronizacion_crear.sql", params).toString(),
                Statement.RETURN_GENERATED_KEYS
            );
            pstmt.execute();
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } catch (Exception e) {
            throw new SQLException(e);
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        }
    }

    public static void finalizar(Sincronizacion obj, Connection conn) throws SQLException {
        PreparedStatement pstmt = null;
        try {
            obj.setFechaHoraFin(new Date());
            obj.setDuracion(obj.getDuracionCalculado());
            
            ParamsSQL params = new ParamsSQL();
            params.setDateTime("fecha_hora_fin", obj.getFechaHoraFin());
            params.setInt("duracion", obj.getDuracion());
            params.setInt("estado", obj.getEstado());
            params.setString("descripcion", obj.getDescripcion());
            params.setInt("registros_consultados", obj.getRegistrosConsultados());
            params.setInt("registros_actualizados", obj.getRegistrosActualizados());
            params.setInt("registros_errores", obj.getRegistrosErrores());
            params.setInt("id_pre", obj.getId());
            
            pstmt = conn.prepareStatement(
                ContextManager.getInstance().getQuery(RUTA_SQL + "sincronizacion_finalizar.sql", params).toString()
            );
            pstmt.execute();
        } catch (Exception e) {
            throw new SQLException(e);
        } finally {
            if (pstmt != null) pstmt.close();
        }
    }
}