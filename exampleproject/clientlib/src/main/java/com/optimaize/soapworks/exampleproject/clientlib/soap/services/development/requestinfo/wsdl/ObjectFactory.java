
package com.optimaize.soapworks.exampleproject.clientlib.soap.services.development.requestinfo.wsdl;

import com.optimaize.soapworks.exampleproject.clientlib.soap.commonwsdl.FaultBean;
import com.optimaize.soapworks.exampleproject.clientlib.soap.commonwsdl.Retry;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.optimaize.soapworks.exampleproject.clientlib.services.system.requestinfo.wsdl package. 
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

    private final static QName _InternalServerErrorWebServiceException_QNAME = new QName("http://requestinfo.development.soap.services.server.exampleproject.soapworks.optimaize.com/", "InternalServerErrorWebServiceException");
    private final static QName _InvalidInputWebServiceException_QNAME = new QName("http://requestinfo.development.soap.services.server.exampleproject.soapworks.optimaize.com/", "InvalidInputWebServiceException");
    private final static QName _RequestInfoResponse_QNAME = new QName("http://requestinfo.development.soap.services.server.exampleproject.soapworks.optimaize.com/", "requestInfoResponse");
    private final static QName _RequestInfo_QNAME = new QName("http://requestinfo.development.soap.services.server.exampleproject.soapworks.optimaize.com/", "requestInfo");
    private final static QName _AccessDeniedWebServiceException_QNAME = new QName("http://requestinfo.development.soap.services.server.exampleproject.soapworks.optimaize.com/", "AccessDeniedWebServiceException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.optimaize.soapworks.exampleproject.clientlib.services.system.requestinfo.wsdl
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
     * Create an instance of {@link RequestInfo }
     * 
     */
    public RequestInfo createRequestInfo() {
        return new RequestInfo();
    }

    /**
     * Create an instance of {@link RequestInfoResponse }
     * 
     */
    public RequestInfoResponse createRequestInfoResponse() {
        return new RequestInfoResponse();
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
    @XmlElementDecl(namespace = "http://requestinfo.development.soap.services.server.exampleproject.soapworks.optimaize.com/", name = "InternalServerErrorWebServiceException")
    public JAXBElement<FaultBean> createInternalServerErrorWebServiceException(FaultBean value) {
        return new JAXBElement<FaultBean>(_InternalServerErrorWebServiceException_QNAME, FaultBean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FaultBean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://requestinfo.development.soap.services.server.exampleproject.soapworks.optimaize.com/", name = "InvalidInputWebServiceException")
    public JAXBElement<FaultBean> createInvalidInputWebServiceException(FaultBean value) {
        return new JAXBElement<FaultBean>(_InvalidInputWebServiceException_QNAME, FaultBean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://requestinfo.development.soap.services.server.exampleproject.soapworks.optimaize.com/", name = "requestInfoResponse")
    public JAXBElement<RequestInfoResponse> createRequestInfoResponse(RequestInfoResponse value) {
        return new JAXBElement<RequestInfoResponse>(_RequestInfoResponse_QNAME, RequestInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RequestInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://requestinfo.development.soap.services.server.exampleproject.soapworks.optimaize.com/", name = "requestInfo")
    public JAXBElement<RequestInfo> createRequestInfo(RequestInfo value) {
        return new JAXBElement<RequestInfo>(_RequestInfo_QNAME, RequestInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FaultBean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://requestinfo.development.soap.services.server.exampleproject.soapworks.optimaize.com/", name = "AccessDeniedWebServiceException")
    public JAXBElement<FaultBean> createAccessDeniedWebServiceException(FaultBean value) {
        return new JAXBElement<FaultBean>(_AccessDeniedWebServiceException_QNAME, FaultBean.class, null, value);
    }

}
