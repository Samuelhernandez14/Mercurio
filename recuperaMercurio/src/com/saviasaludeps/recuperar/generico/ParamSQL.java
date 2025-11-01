/*
 * Consecutivo.java
 *
 * Created on 19 de julio de 2005, 10:04 AM
 */
package com.saviasaludeps.recuperar.generico;

import java.sql.Blob;
import java.io.*;

/**
 * @author Ripalacios - SystemTech Integral.
 */
public class ParamSQL {

    public static final char TIPO_STRING = 'S';
    public static final char TIPO_CHAR = 'C';
    public static final char TIPO_CALE = 'D';
    public static final char TIPO_INT = 'I';
    public static final char TIPO_BYTE = 'B';
    public static final char TIPO_SHORT = 's';
    public static final char TIPO_FLOAT = 'F';
    public static final char TIPO_DOUBLE = 'b';
    public static final char TIPO_INT_NULL = 'i';
    public static final char TIPO_LONG = 'L';
    public static final char TIPO_LONG_NULL = 'n';
    public static final char TIPO_LITERAL = 'l';
    public static final char TIPO_TIME = 'T';
    public static final char TIPO_DATETIME = 'd';
    public static final char TIPO_BLOB = 'O';
    public static final char TIPO_FILE = 'f';

    private char tipo = 'S';
    private String nombre = "";
    private String valor = "";

    public ParamSQL() {

    }

    /**
     * @param val
     */
    public void setNombre(String val) {
        nombre = val;
    }

    /**
     * @param val
     */
    public void setTipo(char val) {
        tipo = val;
    }

    /**
     * @param val
     */
    public void setValor(Long val) {
        valor = String.valueOf(val);
    }

    /**
     * @param val valor de tipo byte
     */
    public void setValor(byte val) {
        valor = String.valueOf(val);
    }

    /**
     * @param val valor de tipo byte
     */
    public void setValor(byte[] val) {
        valor = String.valueOf(val);
    }

    /**
     * @param val Valor de tipo char
     */
    public void setValor(char val) {
        valor = String.valueOf(val);
    }

    /**
     * @param val valor de tipo short
     */
    public void setValor(Short val) {
        valor = String.valueOf(val);
    }

    /**
     * @param val valor de tipo float
     */
    public void setValor(Float val) {
        valor = String.valueOf(val);
    }

    /**
     * @param val valor de tipo double
     */
    public void setValor(Double val) {
        valor = String.valueOf(val);
    }

    /**
     * @param val
     */
    public void setValor(Integer val) {
        valor = String.valueOf(val);
    }

    /**
     * @param val
     */
    public void setValor(String val) {
        valor = String.valueOf(val);
    }

    /**
     * @param val
     */
    public void setValor(Blob val) {
        valor = String.valueOf(val);
    }

    /**
     * @param val
     */
    public void setValor(File val) {
        try {
            InputStream is;
            is = new BufferedInputStream(new FileInputStream(val));
            valor = "";
        } catch (FileNotFoundException e) {
        }
    }

    /**
     * @return la opci�n a la que pertenece el consecutivo
     */
    public String getValor() {
        if (valor == null) {
            valor = "";
        }
        return valor;
    }

    /**
     * @return la referencia usada por l consecutivo.
     */
    public String getNombre() {
        if (nombre == null) {
            nombre = "";
        }
        return nombre;
    }

    /**
     * @return el tipo
     */
    public char getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        String str = "";
        if (nombre != null) {
            str += ",nombre=" + nombre;
        }
        if (valor != null) {
            str += ",valor=" + valor;
        }
        str += ",tipo=" + tipo;
        return str;
    }
}
