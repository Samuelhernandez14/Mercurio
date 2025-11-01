
package com.saviasaludeps.recuperar.wsdl.anexos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ConsultarAnexosRelacionadosRequest complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ConsultarAnexosRelacionadosRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="radConexiones" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nit" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConsultarAnexosRelacionadosRequest", propOrder = {
    "radConexiones",
    "nit"
})
public class ConsultarAnexosRelacionadosRequest {

    @XmlElement(required = true)
    protected String radConexiones;
    @XmlElement(required = true)
    protected String nit;

    /**
     * Obtiene el valor de la propiedad radConexiones.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRadConexiones() {
        return radConexiones;
    }

    /**
     * Define el valor de la propiedad radConexiones.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRadConexiones(String value) {
        this.radConexiones = value;
    }

    /**
     * Obtiene el valor de la propiedad nit.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNit() {
        return nit;
    }

    /**
     * Define el valor de la propiedad nit.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNit(String value) {
        this.nit = value;
    }

}
