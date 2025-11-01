
package com.saviasaludeps.recuperar.wsdl.anexos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para consultarAnexosRelacionados complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="consultarAnexosRelacionados">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://www.servisoft.com.co/Mercurio/Servicios/Contract/ConsultarAnexosRelacionados}ConsultarAnexosRelacionadosRequest" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "consultarAnexosRelacionados", propOrder = {
    "arg0"
})
public class ConsultarAnexosRelacionados {

    protected ConsultarAnexosRelacionadosRequest arg0;

    /**
     * Obtiene el valor de la propiedad arg0.
     * 
     * @return
     *     possible object is
     *     {@link ConsultarAnexosRelacionadosRequest }
     *     
     */
    public ConsultarAnexosRelacionadosRequest getArg0() {
        return arg0;
    }

    /**
     * Define el valor de la propiedad arg0.
     * 
     * @param value
     *     allowed object is
     *     {@link ConsultarAnexosRelacionadosRequest }
     *     
     */
    public void setArg0(ConsultarAnexosRelacionadosRequest value) {
        this.arg0 = value;
    }

}
