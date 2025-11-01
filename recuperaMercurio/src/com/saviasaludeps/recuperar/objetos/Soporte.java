package com.saviasaludeps.recuperar.objetos;

import java.util.Date;

/**
 *
 * @author rpalacios
 */
public class Soporte {

    public static final String EXTENSION_ZIP = "ZIP";
    public static final String EXTENSION_PDF = "PDF";
    public static final String EXTENSION_DOC = "DOC";

    private String idDocumento;
    private String idAnexo;
    private String nombre;
    private String extension;
    private Date fechaHora;
    private String base64;

    public String getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(String idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getIdAnexo() {
        return idAnexo;
    }

    public void setIdAnexo(String idAnexo) {
        this.idAnexo = idAnexo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getNombreExtension() {
        return nombre + "." + extension;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getBase64() {
        return base64;
    }

    public void setBase64(String base64) {
        this.base64 = base64;
    }

}
