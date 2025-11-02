package com.saviasaludeps.siifa.objetos;

import com.saviasaludeps.siifa.generico.ConnectionManagerMySql;
import com.saviasaludeps.siifa.generico.ContextManager;
import com.saviasaludeps.siifa.generico.ParamsSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 *
 * @author rpalacic
 */
public class Sincronizacion {

    private static final String RUTA_SQL = "";

    public static final int ESTADO_EN_PROCESO = 0;
    public static final int ESTADO_FINALIZADO = 1;
    public static final int ESTADO_ERROR = 2;

    private int id;
    private Date periodo;
    private Date fechaHoraInicio;
    private Date fechaHoraFin;
    private int duracion;
    private int estado;
    private String descripcion;
    private int registrosConsultados;
    private int registrosActualizados;

    public Sincronizacion() {

    }

    public Sincronizacion(Date periodo, String descripcion) {
        this.periodo = periodo;
        this.fechaHoraInicio = new Date();
        this.estado = ESTADO_EN_PROCESO;
        this.descripcion = descripcion;
    }

    public Sincronizacion(Date periodo, Date fechaHoraInicio, int estado, String descripcion) {
        this.periodo = periodo;
        this.fechaHoraInicio = fechaHoraInicio;
        this.estado = estado;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Date periodo) {
        this.periodo = periodo;
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

    public int getDuracionCalculo() {
        int minutos = 0;
        if (fechaHoraInicio != null && fechaHoraFin != null) {
            Duration duration = Duration.between(fechaHoraInicio.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(), fechaHoraFin.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            minutos = (int) duration.getSeconds();
        }
        return minutos;
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

    public void incrementarConsultados(int cantidad) {
        this.registrosConsultados += cantidad;
    }

    public void incrementarActualizados() {
        this.registrosActualizados++;
    }

    public static Sincronizacion consultarUltimoExitoso() {
        Sincronizacion obj = null;
        Connection connMySql = null;
        try {
            connMySql = ConnectionManagerMySql.getInstance().getConnection();
            obj = consultarUltimoExitoso(connMySql);
        } catch (SQLException ex) {
            System.err.println("Error SQL: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Error SQL: " + ex.getMessage());
        } finally {
            if (connMySql != null) {
                try {
                    connMySql.close();
                } catch (SQLException ex) {
                    System.err.println("Error SQL: " + ex.getMessage());
                }
            }
        }
        return obj;
    }

    public static Sincronizacion consultarUltimoExitoso(Connection conn) throws Exception {
        Sincronizacion obj = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(ContextManager.getInstance().getQuery(RUTA_SQL + "sincronizacion_consultar_ultimo.sql").toString());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                obj = new Sincronizacion();
                obj.setId(rs.getInt("id"));
                obj.setPeriodo(rs.getDate("periodo"));
                obj.setFechaHoraInicio(rs.getTimestamp("fecha_hora_inicio"));
                obj.setFechaHoraFin(rs.getTimestamp("fecha_hora_fin"));
                obj.setDuracion(rs.getInt("duracion"));
                obj.setEstado(rs.getInt("estado"));
                obj.setDescripcion(rs.getString("descripcion"));
                obj.setRegistrosConsultados(rs.getInt("registros_consultados"));
                obj.setRegistrosActualizados(rs.getInt("registros_actualizados"));
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
        }
        return obj;
    }

    public static int crear(Sincronizacion obj, Connection conn) throws SQLException, Exception {
        int idResp = 0;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            ParamsSQL params = new ParamsSQL();
            params.setDate("periodo", obj.getPeriodo());
            params.setDate("fecha_hora_inicio", obj.getFechaHoraInicio());
            params.setInt("estado", obj.getEstado());
            params.setString("descripcion", obj.getDescripcion());
//            pstmt = conn.prepareStatement(ContextManager.getInstance().getQuery(RUTA_SQL + "sincronizacion_crear.sql", params).toString());
            pstmt = conn.prepareStatement(
                    ContextManager.getInstance().getQuery(RUTA_SQL + "sincronizacion_crear.sql", params).toString(),
                    Statement.RETURN_GENERATED_KEYS
            );
            pstmt.execute();
            rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                idResp = rs.getInt(1); // ← Solo retorna el ID
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
        }
        return idResp;
    }

//    public static void editar(Sincronizacion obj, Connection conn) throws SQLException, Exception {
//        PreparedStatement pstmt = null;
//        try {
//            ParamsSQL params = new ParamsSQL();
//            params.setDate("fecha_hora_fin", obj.getFechaHoraFin());
//            params.setInt("duracion", obj.getDuracion());
//            params.setInt("estado", obj.getEstado());
//            params.setString("descripcion", obj.getDescripcion());
//            params.setInt("registros_consultados", obj.getRegistrosConsultados());
//            params.setInt("registros_actualizados", obj.getRegistrosActualizados());
//            params.setInt("id_pre", obj.getId());
//            pstmt = conn.prepareStatement(ContextManager.getInstance().getQuery(RUTA_SQL + "sincronizacion_editar.sql", params).toString());
//            pstmt.execute();
//        } catch (SQLException ex) {
//            throw ex;
//        } catch (Exception ex) {
//            throw ex;
//        } finally {
//            if (pstmt != null) {
//                pstmt.close();
//            }
//        }
//    }
    public static void actualizar(Sincronizacion obj, Connection conn) throws SQLException, Exception {
        PreparedStatement pstmt = null;
        try {
            ParamsSQL params = new ParamsSQL();
            params.setInt("estado", obj.getEstado());
            params.setString("descripcion", obj.getDescripcion());
            params.setInt("registros_consultados", obj.getRegistrosConsultados());
            params.setInt("registros_actualizados", obj.getRegistrosActualizados());
            params.setInt("id_pre", obj.getId());
            pstmt = conn.prepareStatement(ContextManager.getInstance().getQuery(RUTA_SQL + "sincronizacion_editar.sql", params).toString());
            pstmt.execute();
        } catch (SQLException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }

    public static void finalizar(Sincronizacion obj, Connection conn) throws SQLException, Exception {
        PreparedStatement pstmt = null;
        try {
            ParamsSQL params = new ParamsSQL();
            params.setDate("fecha_hora_fin", new Date());
            params.setInt("duracion", obj.getDuracion());
            params.setInt("estado", obj.getEstado());
            params.setString("descripcion", obj.getDescripcion());
            params.setInt("registros_consultados", obj.getRegistrosConsultados());
            params.setInt("registros_actualizados", obj.getRegistrosActualizados());
            params.setInt("id_pre", obj.getId());
            pstmt = conn.prepareStatement(ContextManager.getInstance().getQuery(RUTA_SQL + "sincronizacion_finalizar.sql", params).toString());
            pstmt.execute();
        } catch (SQLException ex) {
            throw ex;
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }

}
