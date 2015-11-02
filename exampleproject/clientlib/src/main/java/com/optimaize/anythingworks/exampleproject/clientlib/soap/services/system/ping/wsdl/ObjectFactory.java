
package com.optimaize.anythingworks.exampleproject.clientlib.soap.services.system.ping.wsdl;

import com.optimaize.anythingworks.exampleproject.clientlib.soap.commonwsdl.FaultBean;
import com.optimaize.anythingworks.exampleproject.clientlib.soap.commonwsdl.Retry;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.optimaize.soapworks.exampleproject.clientlib.services.system.ping.wsdl package. 
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

    private final static QName _InternalServerErrorWebServiceException_QNAME = new QName("http://ping.system.soap.services.server.exampleproject.soapworks.optimaize.com/", "InternalServerErrorWebServiceException");
    private final static QName _InvalidInputWebServiceException_QNAME = new QName("http://ping.system.soap.services.server.exampleproject.soapworks.optimaize.com/", "InvalidInputWebServiceException");
    private final static QName _PingResponse_QNAME = new QName("http://ping.system.soap.services.server.exampleproject.soapworks.optimaize.com/", "pingResponse");
    private final static QName _Ping_QNAME = new QName("http://ping.system.soap.services.server.exampleproject.soapworks.optimaize.com/", "ping");
    private final static QName _AccessDeniedWebServiceException_QNAME = new QName("http://ping.system.soap.services.server.exampleproject.soapworks.optimaize.com/", "AccessDeniedWebServiceException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.optimaize.soapworks.exampleproject.clientlib.services.system.ping.wsdl
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FaultBean }
     * 
     */
    public FaultBean createFaultBean() {
        return new FaultBean();
    }

    /**
     * Create an instance of {@link Ping }
     * 
     */
    public Ping createPing() {
        return new Ping();
    }

    /**
     * Create an instance of {@link PingResponse }
     * 
     */
    public PingResponse createPingResponse() {
        return new PingResponse();
    }

    /**
     * Create an instance of {@link Retry }
     * 
     */
    public Retry createRetry() {
        return new Retry();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FaultBean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ping.system.soap.services.server.exampleproject.soapworks.optimaize.com/", name = "InternalServerErrorWebServiceException")
    public JAXBElement<FaultBean> createInternalServerErrorWebServiceException(FaultBean value) {
        return new JAXBElement<FaultBean>(_InternalServerErrorWebServiceException_QNAME, FaultBean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FaultBean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ping.system.soap.services.server.exampleproject.soapworks.optimaize.com/", name = "InvalidInputWebServiceException")
    public JAXBElement<FaultBean> createInvalidInputWebServiceException(FaultBean value) {
        return new JAXBElement<FaultBean>(_InvalidInputWebServiceException_QNAME, FaultBean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PingResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ping.system.soap.services.server.exampleproject.soapworks.optimaize.com/", name = "pingResponse")
    public JAXBElement<PingResponse> createPingResponse(PingResponse value) {
        return new JAXBElement<PingResponse>(_PingResponse_QNAME, PingResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Ping }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ping.system.soap.services.server.exampleproject.soapworks.optimaize.com/", name = "ping")
    public JAXBElement<Ping> createPing(Ping value) {
        return new JAXBElement<Ping>(_Ping_QNAME, Ping.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FaultBean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ping.system.soap.services.server.exampleproject.soapworks.optimaize.com/", name = "AccessDeniedWebServiceException")
    public JAXBElement<FaultBean> createAccessDeniedWebServiceException(FaultBean value) {
        return new JAXBElement<FaultBean>(_AccessDeniedWebServiceException_QNAME, FaultBean.class, null, value);
    }

}
