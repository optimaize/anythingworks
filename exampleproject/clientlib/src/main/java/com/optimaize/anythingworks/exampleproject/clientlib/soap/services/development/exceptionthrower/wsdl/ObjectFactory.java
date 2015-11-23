
package com.optimaize.anythingworks.exampleproject.clientlib.soap.services.development.exceptionthrower.wsdl;

import com.optimaize.anythingworks.exampleproject.clientlib.soap.commonwsdl.FaultBean;
import com.optimaize.anythingworks.exampleproject.clientlib.soap.commonwsdl.Retry;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.optimaize.anythingworks.exampleproject.clientlib.soap.services.development.exceptionthrower.wsdl package. 
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

    private final static QName _ThrowExceptionResponse_QNAME = new QName("http://exceptionthrower.development.soap.services.server.exampleproject.anythingworks.optimaize.com/", "throwExceptionResponse");
    private final static QName _SoapWebServiceException_QNAME = new QName("http://exceptionthrower.development.soap.services.server.exampleproject.anythingworks.optimaize.com/", "SoapWebServiceException");
    private final static QName _ThrowException_QNAME = new QName("http://exceptionthrower.development.soap.services.server.exampleproject.anythingworks.optimaize.com/", "throwException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.optimaize.anythingworks.exampleproject.clientlib.soap.services.development.exceptionthrower.wsdl
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ThrowExceptionResponse }
     * 
     */
    public ThrowExceptionResponse createThrowExceptionResponse() {
        return new ThrowExceptionResponse();
    }

    /**
     * Create an instance of {@link ThrowException }
     * 
     */
    public ThrowException createThrowException() {
        return new ThrowException();
    }

    /**
     * Create an instance of {@link FaultBean }
     * 
     */
    public FaultBean createFaultBean() {
        return new FaultBean();
    }

    /**
     * Create an instance of {@link Retry }
     * 
     */
    public Retry createRetry() {
        return new Retry();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ThrowExceptionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://exceptionthrower.development.soap.services.server.exampleproject.anythingworks.optimaize.com/", name = "throwExceptionResponse")
    public JAXBElement<ThrowExceptionResponse> createThrowExceptionResponse(ThrowExceptionResponse value) {
        return new JAXBElement<ThrowExceptionResponse>(_ThrowExceptionResponse_QNAME, ThrowExceptionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FaultBean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://exceptionthrower.development.soap.services.server.exampleproject.anythingworks.optimaize.com/", name = "SoapWebServiceException")
    public JAXBElement<FaultBean> createSoapWebServiceException(FaultBean value) {
        return new JAXBElement<FaultBean>(_SoapWebServiceException_QNAME, FaultBean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ThrowException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://exceptionthrower.development.soap.services.server.exampleproject.anythingworks.optimaize.com/", name = "throwException")
    public JAXBElement<ThrowException> createThrowException(ThrowException value) {
        return new JAXBElement<ThrowException>(_ThrowException_QNAME, ThrowException.class, null, value);
    }

}
