
package com.saviasaludeps.recuperar.wsdl.anexos;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.saviasaludeps.recuperar.wsdl.anexos package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ConsultarAnexosRelacionadosResponse_QNAME = new QName("http://www.servisoft.com.co/Mercurio/Servicios/Contract/ConsultarAnexosRelacionados", "consultarAnexosRelacionadosResponse");
    private final static QName _Transaccion_QNAME = new QName("http://www.servisoft.com.co/Mercurio/Servicios/Schema/Transaccion", "Transaccion");
    private final static QName _ConsultarAnexosRelacionados_QNAME = new QName("http://www.servisoft.com.co/Mercurio/Servicios/Contract/ConsultarAnexosRelacionados", "consultarAnexosRelacionados");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.saviasaludeps.recuperar.wsdl.anexos
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Transaccion }
     * 
     */
    public Transaccion createTransaccion() {
        return new Transaccion();
    }

    /**
     * Create an instance of {@link ConsultarAnexosRelacionadosResponse }
     * 
     */
    public ConsultarAnexosRelacionadosResponse createConsultarAnexosRelacionadosResponse() {
        return new ConsultarAnexosRelacionadosResponse();
    }

    /**
     * Create an instance of {@link ConsultarAnexosRelacionados }
     * 
     */
    public ConsultarAnexosRelacionados createConsultarAnexosRelacionados() {
        return new ConsultarAnexosRelacionados();
    }

    /**
     * Create an instance of {@link ConsultarAnexosRelacionadosRequest }
     * 
     */
    public ConsultarAnexosRelacionadosRequest createConsultarAnexosRelacionadosRequest() {
        return new ConsultarAnexosRelacionadosRequest();
    }

    /**
     * Create an instance of {@link ConsultarAnexosRelacionadosResponseAlt }
     * 
     */
    public ConsultarAnexosRelacionadosResponseAlt createConsultarAnexosRelacionadosResponseAlt() {
        return new ConsultarAnexosRelacionadosResponseAlt();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultarAnexosRelacionadosResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.servisoft.com.co/Mercurio/Servicios/Contract/ConsultarAnexosRelacionados", name = "consultarAnexosRelacionadosResponse")
    public JAXBElement<ConsultarAnexosRelacionadosResponse> createConsultarAnexosRelacionadosResponse(ConsultarAnexosRelacionadosResponse value) {
        return new JAXBElement<ConsultarAnexosRelacionadosResponse>(_ConsultarAnexosRelacionadosResponse_QNAME, ConsultarAnexosRelacionadosResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Transaccion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.servisoft.com.co/Mercurio/Servicios/Schema/Transaccion", name = "Transaccion")
    public JAXBElement<Transaccion> createTransaccion(Transaccion value) {
        return new JAXBElement<Transaccion>(_Transaccion_QNAME, Transaccion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultarAnexosRelacionados }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.servisoft.com.co/Mercurio/Servicios/Contract/ConsultarAnexosRelacionados", name = "consultarAnexosRelacionados")
    public JAXBElement<ConsultarAnexosRelacionados> createConsultarAnexosRelacionados(ConsultarAnexosRelacionados value) {
        return new JAXBElement<ConsultarAnexosRelacionados>(_ConsultarAnexosRelacionados_QNAME, ConsultarAnexosRelacionados.class, null, value);
    }

}
