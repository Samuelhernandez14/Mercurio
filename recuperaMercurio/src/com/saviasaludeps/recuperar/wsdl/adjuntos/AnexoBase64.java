
package com.saviasaludeps.recuperar.wsdl.adjuntos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para AnexoBase64 complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="AnexoBase64">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idAnexo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fechaAnexo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="titulo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="extension" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="documentoBase64" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AnexoBase64", propOrder = {
    "idAnexo",
    "fechaAnexo",
    "titulo",
    "extension",
    "documentoBase64"
})
public class AnexoBase64 {

    protected String idAnexo;
    protected String fechaAnexo;
    protected String titulo;
    protected String extension;
    protected String documentoBase64;

    /**
     * Obtiene el valor de la propiedad idAnexo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdAnexo() {
        return idAnexo;
    }

    /**
     * Define el valor de la propiedad idAnexo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdAnexo(String value) {
        this.idAnexo = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaAnexo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaAnexo() {
        return fechaAnexo;
    }

    /**
     * Define el valor de la propiedad fechaAnexo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaAnexo(String value) {
        this.fechaAnexo = value;
    }

    /**
     * Obtiene el valor de la propiedad titulo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Define el valor de la propiedad titulo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitulo(String value) {
        this.titulo = value;
    }

    /**
     * Obtiene el valor de la propiedad extension.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtension() {
        return extension;
    }

    /**
     * Define el valor de la propiedad extension.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtension(String value) {
        this.extension = value;
    }

    /**
     * Obtiene el valor de la propiedad documentoBase64.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocumentoBase64() {
        return documentoBase64;
    }

    /**
     * Define el valor de la propiedad documentoBase64.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocumentoBase64(String value) {
        this.documentoBase64 = value;
    }

}
