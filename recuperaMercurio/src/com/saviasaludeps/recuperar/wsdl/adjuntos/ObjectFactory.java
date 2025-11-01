
package com.saviasaludeps.recuperar.wsdl.adjuntos;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.saviasaludeps.recuperar.wsdl.adjuntos package. 
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

    private final static QName _ConsultarAnexoRadicadoResponse_QNAME = new QName("http://www.servisoft.com.co/Mercurio/Servicios/Contract/ConsultarAnexoRadOrigen", "consultarAnexoRadicadoResponse");
    private final static QName _Transaccion_QNAME = new QName("http://www.servisoft.com.co/Mercurio/Servicios/Schema/Transaccion", "Transaccion");
    private final static QName _ConsultarAnexoRadicado_QNAME = new QName("http://www.servisoft.com.co/Mercurio/Servicios/Contract/ConsultarAnexoRadOrigen", "consultarAnexoRadicado");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.saviasaludeps.recuperar.wsdl.adjuntos
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ConsultarAnexoRadicado }
     * 
     */
    public ConsultarAnexoRadicado createConsultarAnexoRadicado() {
        return new ConsultarAnexoRadicado();
    }

    /**
     * Create an instance of {@link ConsultarAnexoRadicadoResponse }
     * 
     */
    public ConsultarAnexoRadicadoResponse createConsultarAnexoRadicadoResponse() {
        return new ConsultarAnexoRadicadoResponse();
    }

    /**
     * Create an instance of {@link AnexoRadicadoRequest }
     * 
     */
    public AnexoRadicadoRequest createAnexoRadicadoRequest() {
        return new AnexoRadicadoRequest();
    }

    /**
     * Create an instance of {@link AnexoBase64 }
     * 
     */
    public AnexoBase64 createAnexoBase64() {
        return new AnexoBase64();
    }

    /**
     * Create an instance of {@link AnexoRadicadoResponse }
     * 
     */
    public AnexoRadicadoResponse createAnexoRadicadoResponse() {
        return new AnexoRadicadoResponse();
    }

    /**
     * Create an instance of {@link Transaccion }
     * 
     */
    public Transaccion createTransaccion() {
        return new Transaccion();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultarAnexoRadicadoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.servisoft.com.co/Mercurio/Servicios/Contract/ConsultarAnexoRadOrigen", name = "consultarAnexoRadicadoResponse")
    public JAXBElement<ConsultarAnexoRadicadoResponse> createConsultarAnexoRadicadoResponse(ConsultarAnexoRadicadoResponse value) {
        return new JAXBElement<ConsultarAnexoRadicadoResponse>(_ConsultarAnexoRadicadoResponse_QNAME, ConsultarAnexoRadicadoResponse.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link ConsultarAnexoRadicado }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.servisoft.com.co/Mercurio/Servicios/Contract/ConsultarAnexoRadOrigen", name = "consultarAnexoRadicado")
    public JAXBElement<ConsultarAnexoRadicado> createConsultarAnexoRadicado(ConsultarAnexoRadicado value) {
        return new JAXBElement<ConsultarAnexoRadicado>(_ConsultarAnexoRadicado_QNAME, ConsultarAnexoRadicado.class, null, value);
    }

}
