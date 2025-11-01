
package com.saviasaludeps.recuperar.wsdl.adjuntos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para consultarAnexoRadicadoResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="consultarAnexoRadicadoResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://www.servisoft.com.co/Mercurio/Servicios/Contract/ConsultarAnexoRadOrigen}AnexoRadicadoResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultarAnexoRadicadoResponse", propOrder = {
    "_return"
})
public class ConsultarAnexoRadicadoResponse {

    @XmlElement(name = "return")
    protected AnexoRadicadoResponse _return;

    /**
     * Obtiene el valor de la propiedad return.
     * 
     * @return
     *     possible object is
     *     {@link AnexoRadicadoResponse }
     *     
     */
    public AnexoRadicadoResponse getReturn() {
        return _return;
    }

    /**
     * Define el valor de la propiedad return.
     * 
     * @param value
     *     allowed object is
     *     {@link AnexoRadicadoResponse }
     *     
     */
    public void setReturn(AnexoRadicadoResponse value) {
        this._return = value;
    }

}
