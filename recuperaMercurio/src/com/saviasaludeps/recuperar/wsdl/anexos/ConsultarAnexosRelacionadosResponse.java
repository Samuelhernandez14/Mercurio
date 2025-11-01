
package com.saviasaludeps.recuperar.wsdl.anexos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para consultarAnexosRelacionadosResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="consultarAnexosRelacionadosResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://www.servisoft.com.co/Mercurio/Servicios/Contract/ConsultarAnexosRelacionados}ConsultarAnexosRelacionadosResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultarAnexosRelacionadosResponse", propOrder = {
    "_return"
})
public class ConsultarAnexosRelacionadosResponse {

    @XmlElement(name = "return")
    protected ConsultarAnexosRelacionadosResponseAlt _return;

    /**
     * Obtiene el valor de la propiedad return.
     * 
     * @return
     *     possible object is
     *     {@link ConsultarAnexosRelacionadosResponseAlt }
     *     
     */
    public ConsultarAnexosRelacionadosResponseAlt getReturn() {
        return _return;
    }

    /**
     * Define el valor de la propiedad return.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsultarAnexosRelacionadosResponseAlt }
     *     
     */
    public void setReturn(ConsultarAnexosRelacionadosResponseAlt value) {
        this._return = value;
    }

}
