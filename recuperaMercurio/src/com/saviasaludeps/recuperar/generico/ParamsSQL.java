/*
 * ParamSQL.java
 *
 * Created on 19 de julio de 2005, 04:32 PM
 */
package com.saviasaludeps.recuperar.generico;

import java.sql.Blob;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ripalcios - SystemTech Integral.
 */
public class ParamsSQL {

    private List params = null;

    /**
     * Creates a new instance of ParamSQL
     */
    public ParamsSQL() {
        params = new ArrayList();
    }

    /**
     * <b>Creacion: Ripalacios/01-sep-2006 - SystemTech Integral</b><br>
     * Limpia la lista de parametros.
     */
    public void limpiar() {
        params.clear();
    }

    /**
     * <b>Creación: Ripalacios/24-jul-2011 - SeInfor</b><br>
     * Agrega un parámetro de tipo Boolean ingresado por un int a la lista de
     * parámetros
     *
     * @param nombre Nombre del parámetro a agregar a la lista.
     * @param valor Valor del parámetro a agregar.
     */
    public void setBoolean(String nombre, boolean valor) {
        ParamSQL param = new ParamSQL();
        param.setNombre(nombre);
        if (valor) {
            param.setValor(1);
        } else {
            param.setValor(0);
        }
        param.setTipo(ParamSQL.TIPO_INT);
        this.agregar(param);
    }

    /**
     * <b>Creacion: Ripalacios/01-sep-2006 - SystemTech Integral</b><br>
     * Agrega un parametro de tipo string a la lista de parametros
     *
     * @param nombre Nombre del parametro a agregar a la lista.
     * @param valor Valor del parametro a agregar.
     */
    public void setString(String nombre, String valor) {
        ParamSQL param = new ParamSQL();
        param.setNombre(nombre);
        param.setValor(valor);
        if (valor == null || valor.toUpperCase().equals("NULL")) {
            param.setValor(valor);
            param.setTipo(ParamSQL.TIPO_LITERAL);
        } else {
            param.setValor(valor.trim().replace("'", ""));
            param.setTipo(ParamSQL.TIPO_STRING);
        }
        this.agregar(param);
    }

    /**
     * <b>Creaci�n: Ripalacios/01-sep-2006 - SystemTech Integral</b><br>
     * Agrega un par�metro de tipo char a la lista de par�metros
     *
     * @param nombre Nombre del par�metro a agregar a la lista.
     * @param valor Valor del par�metro a agregar.
     */
    public void setChar(String nombre, char valor) {
        ParamSQL param = new ParamSQL();
        param.setNombre(nombre);
        param.setValor(valor);
        param.setTipo(ParamSQL.TIPO_CHAR);
        this.agregar(param);
    }

    /**
     * <b>Creacion: Ripalacios/01-sep-2006 - SystemTech Integral</b><br>
     * Agrega un parametro de tipo literal (un string sin comillas, se usa para
     * nombres de campo en sql dinamicos) a la lista de parametros
     *
     *
     * @param nombre
     * @param literal
     */
    public void setLiteral(String nombre, String literal) {
        ParamSQL param = new ParamSQL();
        param.setNombre(nombre);
        param.setValor(literal);
        param.setTipo(ParamSQL.TIPO_LITERAL);
        this.agregar(param);
    }

    /**
     * <b>Creaci�n: Ripalacios/01-sep-2006 - SystemTech Integral</b><br>
     * Agrega un par�metro de tipo long a la lista de par�metros, si su valor es
     * cero, se tomar� null para los sql.
     *
     *
     * @param nombre
     * @param valor
     */
    public void setLong(String nombre, Long valor) {
        ParamSQL param = new ParamSQL();
        param.setNombre(nombre);
        if (valor == null) {
            param.setTipo(ParamSQL.TIPO_LONG_NULL);
        } else {
            param.setTipo(ParamSQL.TIPO_LONG);
        }
        param.setValor(valor);
        this.agregar(param);
    }

    /**
     * <b>Creaci�n: Ripalacios/01-sep-2006 - SystemTech Integral</b><br>
     * Agrega un par�metro de tipo int a la lista de par�metros
     *
     * @param nombre Nombre del par�metro a agregar a la lista.
     * @param valor Valor del par�metro a agregar, si el valor es cero se tomar�
     * como null.
     */
    public void setInt(String nombre, Integer valor) {
        ParamSQL param = new ParamSQL();
        param.setNombre(nombre);
        if (valor == null) {
            param.setTipo(ParamSQL.TIPO_INT_NULL);
        } else {
            param.setTipo(ParamSQL.TIPO_INT);
        }
        param.setValor(valor);
        this.agregar(param);
    }

//    /**
//     * <b>Creaci�n: Ripalacios/01-sep-2006 - SystemTech Integral</b><br>
//     * Agrega un par�metro de tipo int a la lista de par�metros
//     *
//     * @param nombre Nombre del par�metro a agregar a la lista.
//     * @param valor del par�metro a agregar.
//     */
//    public void setInt(String nombre, int valor) {
//        ParamSQL param = new ParamSQL();
//        param.setNombre(nombre);
//        param.setValor(valor);
//        param.setTipo(ParamSQL.TIPO_INT);
//        this.agregar(param);
//    }
    /**
     * <b>Creación: Ripalacios/01-sep-2006 - SystemTech Integral</b><br>
     * Agrega un par�metro de tipo byte a la lista de par�metros
     *
     * @param nombre Nombre del par�metro a agregar a la lista.
     * @param valor del par�metro a agregar.
     */
    public void setByte(String nombre, byte valor) {
        ParamSQL param = new ParamSQL();
        param.setNombre(nombre);
        param.setValor(valor);
        param.setTipo(ParamSQL.TIPO_BYTE);
        this.agregar(param);
    }

    /**
     * <b>Creaci�n: Ripalacios/01-sep-2006 - SystemTech Integral</b><br>
     * Agrega un par�metro de tipo byte a la lista de par�metros
     *
     * @param nombre Nombre del par�metro a agregar a la lista.
     * @param valor del par�metro a agregar.
     */
    public void setBytes(String nombre, byte[] valor) {
        ParamSQL param = new ParamSQL();
        param.setNombre(nombre);
        param.setValor(valor);
        param.setTipo(ParamSQL.TIPO_BYTE);
        this.agregar(param);
    }

    /**
     * <b>Creaci�n: Ripalacios/01-sep-2006 - SystemTech Integral</b><br>
     * Agrega un par�metro de tipo short a la lista de par�metros
     *
     * @param nombre Nombre del par�metro a agregar a la lista.
     * @param valor del par�metro a agregar.
     */
    public void setShort(String nombre, short valor) {
        ParamSQL param = new ParamSQL();
        param.setNombre(nombre);
        param.setValor(valor);
        param.setTipo(ParamSQL.TIPO_SHORT);
        this.agregar(param);
    }

    /**
     * <b>Creaci�n: Ripalacios/01-sep-2006 - SystemTech Integral</b><br>
     * Agrega un par�metro de tipo float a la lista de par�metros
     *
     * @param nombre Nombre del par�metro a agregar a la lista.
     * @param valor del par�metro a agregar.
     */
    public void setFloat(String nombre, float valor) {
        ParamSQL param = new ParamSQL();
        param.setNombre(nombre);
        param.setValor(valor);
        param.setTipo(ParamSQL.TIPO_FLOAT);
        this.agregar(param);
    }

    /**
     * <b>Creaci�n: Ripalacios/01-sep-2006 - SystemTech Integral</b><br>
     * Agrega un par�metro de tipo float a la lista de par�metros
     *
     * @param nombre Nombre del par�metro a agregar a la lista.
     * @param valor del par�metro a agregar.
     */
    public void setDouble(String nombre, double valor) {
        ParamSQL param = new ParamSQL();
        param.setNombre(nombre);
        param.setValor(valor);
        param.setTipo(ParamSQL.TIPO_DOUBLE);
        this.agregar(param);
    }

//    /**
//     * <b>Creaci�n: Ripalacios/01-sep-2006 - SystemTech Integral</b><br>
//     * Agrega un par�metro de tipo long a la lista de par�metros
//     *
//     * @param nombre Nombre del par�metro a agregar a la lista.
//     * @param valor del par�metro a agregar.
//     */
//    public void setLong(String nombre, long valor) {
//        ParamSQL param = new ParamSQL();
//        param.setNombre(nombre);
//        param.setValor(valor);
//        param.setTipo(ParamSQL.TIPO_LONG);
//        this.agregar(param);
//    }
    /**
     * <b>Creaci�n: Ripalacios/01-sep-2006 - SystemTech Integral</b><br>
     * Agrega un par�metro de tipo Blob a la lista de par�metros
     *
     * @param nombre Nombre del par�metro a agregar a la lista.
     * @param valor del par�metro a agregar.
     */
    public void setBlob(String nombre, Blob valor) {
        ParamSQL param = new ParamSQL();
        param.setNombre(nombre);
        param.setValor(valor);
        param.setTipo(ParamSQL.TIPO_BLOB);
        this.agregar(param);
    }

    /**
     * <b>Creaci�n: Ripalacios/01-sep-2006 - SystemTech Integral</b><br>
     * Agrega un par�metro de tipo File a la lista de par�metros
     *
     * @param nombre Nombre del par�metro a agregar a la lista.
     * @param valor del par�metro a agregar.
     */
    public void setFile(String nombre, File valor) {
        ParamSQL param = new ParamSQL();
        param.setNombre(nombre);
        param.setValor(valor);
        param.setTipo(ParamSQL.TIPO_FILE);
        this.agregar(param);
    }

    /**
     * <b>Creacion: Ripalacios/01-sep-2006 - SystemTech Integral</b><br>
     * Agrega un parametro de tipo GregorianCalendar a la lista de
     * parametros<br>
     * cuando se remplace este valor en el sql, se encargar� de asignar
     * TO_DATE(valor,'DD-MM-YYYY')
     *
     * @param nombre Nombre del par�metro a agregar a la lista.
     * @param fecha Valor del par�metro a agregar.
     */
    public void setDate(String nombre, Date fecha) {
        ParamSQL param = new ParamSQL();
        param.setNombre(nombre);
        if (fecha == null) {
            param.setValor("NULL");
            param.setTipo(ParamSQL.TIPO_LITERAL);
        } else {
            param.setValor(new SimpleDateFormat("yyyy-MM-dd").format(fecha));
            param.setTipo(ParamSQL.TIPO_CALE);
        }
        this.agregar(param);
    }

    /**
     * <b>Creaci�n: Ripalacios/01-sep-2006 - SystemTech Integral</b><br>
     * Agrega un par�metro de tipo GregorianCalendar a la lista de
     * par�metros<br>
     * cuando se remplace este valor en el sql, se encargar� de asignar
     * TO_DATE(valor,'DD-MM-YYYY HH:MI AM')
     *
     * @param nombre Nombre del par�metro a agregar.
     * @param fechaHora Valor del par�metro a agregar.
     */
    public void setDateTime(String nombre, Date fechaHora) {
        ParamSQL param = new ParamSQL();
        param.setNombre(nombre);
        if (fechaHora == null) {
            param.setValor("NULL");
            param.setTipo(ParamSQL.TIPO_LITERAL);
        } else {
            param.setValor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(fechaHora));
            param.setTipo(ParamSQL.TIPO_DATETIME);
        }
        this.agregar(param);
    }

    /**
     * <b>Creaci�n: Ripalacios/01-sep-2006 - SystemTech Integral</b><br>
     * Agrega un par�metro de tipo GregorianCalendar a la lista de par�metros y
     * tomar� unicamente la hora al momento de reemplazar los par�metros en el
     * SQL<br>
     * cuando remplace este valor en el sql, se encargar� de asihnar
     * TO_DATE(valor,'HH:MI AM')
     *
     * @param nombre
     * @param hora
     */
    public void setTime(String nombre, Date hora) {
        ParamSQL param = new ParamSQL();
        param.setNombre(nombre);
        if (hora == null) {
            param.setValor("NULL");
            param.setTipo(ParamSQL.TIPO_LITERAL);
        } else {
            param.setValor(new SimpleDateFormat("HH:mm:ss").format(hora));
            param.setTipo(ParamSQL.TIPO_TIME);
        }
        this.agregar(param);
    }

    /**
     * <b>Creaci�n: Ripalacios/01-sep-2006 - SystemTech Integral</b><br>
     * Obtiene el valor de un par�metro dado<br>
     * Si es fecha lo convierte a formato: TO_DATE(valor,'DD-MM-YYYY') <br>
     * Si es hora lo convierte a formato: TO_DATE(valor,'HH:MI AM') <br>
     * Si es vacio, pone la palabra NULL.
     *
     * @param nombre
     * @return
     */
    public String getValor(String nombre) {
        ParamSQL param = null;
        String valor = null;
        for (int i = 0; i < params.size(); i++) {
            param = (ParamSQL) params.get(i);
            if (param.getNombre().equals(nombre)) {
                valor = param.getValor();
                break;
            }
        }
        if (valor != null) {
            if (valor.equals("")
                    || (valor.equals("0")
                    && (param.getTipo() == ParamSQL.TIPO_INT_NULL || param.getTipo() == ParamSQL.TIPO_LONG_NULL))) {
                valor = "NULL";
            } else {
                switch (param.getTipo()) {
                    case ParamSQL.TIPO_STRING:
                    case ParamSQL.TIPO_CHAR:
                        valor = "'" + valor + "'";
                        break;
                    case ParamSQL.TIPO_CALE:
                        valor = "'" + valor + "'";
//                    valor = "TO_DATE('" + valor + "','DD-MM-YYYY')";
                        break;
                    case ParamSQL.TIPO_TIME:
                        valor = "'" + valor + "'";
//                    valor = "TO_DATE('" + valor + "','HH:MI AM')";
                        break;
                    case ParamSQL.TIPO_DATETIME:
                        valor = "'" + valor + "'";
//                    valor = "TO_DATE('" + valor + "','DD-MM-YYYY HH:MI AM')";
                        break;
                    default:
                        break;
                }
            }
        } else {
            valor = "PARAMETRO_" + nombre + "_NO_DEFINIDO";
        }
        return valor;
    }// getValor

    /**
     * <b>Creaci�n: Ripalacios/01-sep-2006 - SystemTech Integral</b><br>
     * agrega un objeto par�metro a la lista.
     */
    private void agregar(ParamSQL param) {
//        ParamSQL paramAct;
        boolean bEncontrado = false;
        for (int i = 0; i < params.size(); i++) {
            ParamSQL paramAct = (ParamSQL) params.get(i);
            if (paramAct.getNombre().equals(param.getNombre())) {
                params.set(i, param);
                bEncontrado = true;
                break;
            }
        }
        if (!bEncontrado) {
            params.add(param);
        }
    }
}
