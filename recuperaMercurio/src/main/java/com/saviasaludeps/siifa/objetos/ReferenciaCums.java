package com.saviasaludeps.siifa.objetos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReferenciaCums {
    
    @JsonProperty("idCums")
    private Integer idCums;
    
    @JsonProperty("expedienteCum")
    private Integer expedienteCum;
    
    @JsonProperty("consecutivoCum")
    private Integer consecutivoCum;
    
    @JsonProperty("nombre")
    private String nombre;
    
    @JsonProperty("descripcion")
    private String descripcion;
    
    @JsonProperty("codigoAtc")
    private String codigoAtc;
    
    @JsonProperty("incluidoPbs")
    private Boolean incluidoPbs;
    
    public ReferenciaCums() {
    }

    public Integer getIdCums() {
        return idCums;
    }

    public void setIdCums(Integer idCums) {
        this.idCums = idCums;
    }

    public Integer getExpedienteCum() {
        return expedienteCum;
    }

    public void setExpedienteCum(Integer expedienteCum) {
        this.expedienteCum = expedienteCum;
    }

    public Integer getConsecutivoCum() {
        return consecutivoCum;
    }

    public void setConsecutivoCum(Integer consecutivoCum) {
        this.consecutivoCum = consecutivoCum;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigoAtc() {
        return codigoAtc;
    }

    public void setCodigoAtc(String codigoAtc) {
        this.codigoAtc = codigoAtc;
    }

    public Boolean getIncluidoPbs() {
        return incluidoPbs;
    }

    public void setIncluidoPbs(Boolean incluidoPbs) {
        this.incluidoPbs = incluidoPbs;
    }
}