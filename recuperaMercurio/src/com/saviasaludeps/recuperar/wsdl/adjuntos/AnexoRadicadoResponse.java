
package com.saviasaludeps.recuperar.wsdl.adjuntos;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para AnexoRadicadoResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="AnexoRadicadoResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="resultado" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="transaccion" type="{http://www.servisoft.com.co/Mercurio/Servicios/Schema/Transaccion}Transaccion"/>
 *         &lt;element name="idDocumentoMercurio" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="anexos" type="{http://www.servisoft.com.co/Mercurio/Servicios/Contract/ConsultarAnexoRadOrigen}AnexoBase64" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AnexoRadicadoResponse", propOrder = {
    "resultado",
    "transaccion",
    "idDocumentoMercurio",
    "anexos"
})
public class AnexoRadicadoResponse {

    protected boolean resultado;
    @XmlElement(required = true)
    protected Transaccion transaccion;
    @XmlElement(required = true)
    protected String idDocumentoMercurio;
    @XmlElement(required = true)
    protected List<AnexoBase64> anexos;

    /**
     * Obtiene el valor de la propiedad resultado.
     * 
     */
    public boolean isResultado() {
        return resultado;
    }

    /**
     * Define el valor de la propiedad resultado.
     * 
     */
    public void setResultado(boolean value) {
        this.resultado = value;
    }

    /**
     * Obtiene el valor de la propiedad transaccion.
     * 
     * @return
     *     possible object is
     *     {@link Transaccion }
     *     
     */
    public Transaccion getTransaccion() {
        return transaccion;
    }

    /**
     * Define el valor de la propiedad transaccion.
     * 
     * @param value
     *     allowed object is
     *     {@link Transaccion }
     *     
     */
    public void setTransaccion(Transaccion value) {
        this.transaccion = value;
    }

    /**
     * Obtiene el valor de la propiedad idDocumentoMercurio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdDocumentoMercurio() {
        return idDocumentoMercurio;
    }

    /**
     * Define el valor de la propiedad idDocumentoMercurio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdDocumentoMercurio(String value) {
        this.idDocumentoMercurio = value;
    }

    /**
     * Gets the value of the anexos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the anexos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAnexos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AnexoBase64 }
     * 
     * 
     */
    public List<AnexoBase64> getAnexos() {
        if (anexos == null) {
            anexos = new ArrayList<AnexoBase64>();
        }
        return this.anexos;
    }

}
