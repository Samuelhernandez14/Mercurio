package com.saviasaludeps.siifa.objetos;

import com.saviasaludeps.siifa.generico.ContextManager;
import com.saviasaludeps.siifa.generico.Log;
import com.saviasaludeps.siifa.generico.ParamsSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author lfriverad
 */
public class Maestro {

    private static final String RUTA_SQL = "";
    public static final String MAE_TIPO_SOPORTE_CODIGO_DESCONOCIDO = "XXX";
    public static final String MAE_TIPO_SOPORTE_VALOR_DESCONOCIDO = "Desconocido";

    private Integer id;
    private String nombre = "";
    private String valor = "";
    private String tipo = "";
    private boolean activo = true;

    public Maestro() {
    }

    public Maestro(Integer id) {
        this.id = id;
    }

    public Maestro(Integer id, String nombre, String tipo, String valor, String descripcion, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.valor = valor;
        this.activo = activo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isActivo() {
        return activo;
    }

    public String getActivoStr() {
        return (isActivo()) ? "SI" : "NO";
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Maestro{" + "id=" + id + ", nombre=" + nombre + ", valor=" + valor + ", tipo=" + tipo + ", activo=" + activo + '}';
    }

    public static Maestro consultarMaestro(Connection conn, String valor) {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Maestro maestro = null;

        try {
            ParamsSQL params = new ParamsSQL();
            params.setString("valor_pre", valor);
            String sql = ContextManager.getInstance().getQuery(RUTA_SQL + "maestros_consultar.sql", params).toString();
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();

            if (rs.next()) {
                maestro = new Maestro();
                maestro.setId(rs.getInt("id"));
                maestro.setValor(rs.getString("valor"));
                maestro.setNombre(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            Log.getInstance().error("Error consultando Maestro", e.getMessage(), e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstm != null) {
                    pstm.close();
                }
            } catch (SQLException ex) {
                Log.getInstance().error("Error cerrando recursos", ex.getMessage(), ex);
            }
        }

        return maestro;
    }
}
