
package com.optimaize.soapworks.exampleproject.clientlib.soap.services.development.exceptionthrower.wsdl;

import com.optimaize.soapworks.exampleproject.clientlib.soap.commonwsdl.FaultBean;
import com.optimaize.soapworks.exampleproject.clientlib.soap.commonwsdl.Retry;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.optimaize.soapworks.exampleproject.clientlib.services.system.exceptionthrower.wsdl package. 
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

    private final static QName _ThrowAccessDeniedRequestLimitExceeded_QNAME = new QName("http://exceptionthrower.development.soap.services.server.exampleproject.soapworks.optimaize.com/", "throwAccessDeniedRequestLimitExceeded");
    private final static QName _ThrowAccessDeniedRequestLimitExceededResponse_QNAME = new QName("http://exceptionthrower.development.soap.services.server.exampleproject.soapworks.optimaize.com/", "throwAccessDeniedRequestLimitExceededResponse");
    private final static QName _ThrowInternalResponse_QNAME = new QName("http://exceptionthrower.development.soap.services.server.exampleproject.soapworks.optimaize.com/", "throwInternalResponse");
    private final static QName _ThrowAccessDeniedTooManyConcurrentRequestsResponse_QNAME = new QName("http://exceptionthrower.development.soap.services.server.exampleproject.soapworks.optimaize.com/", "throwAccessDeniedTooManyConcurrentRequestsResponse");
    private final static QName _ThrowAccessDeniedNoSuchAccountResponse_QNAME = new QName("http://exceptionthrower.development.soap.services.server.exampleproject.soapworks.optimaize.com/", "throwAccessDeniedNoSuchAccountResponse");
    private final static QName _AccessDeniedWebServiceException_QNAME = new QName("http://exceptionthrower.development.soap.services.server.exampleproject.soapworks.optimaize.com/", "AccessDeniedWebServiceException");
    private final static QName _ThrowAccessDeniedNoSuchAccount_QNAME = new QName("http://exceptionthrower.development.soap.services.server.exampleproject.soapworks.optimaize.com/", "throwAccessDeniedNoSuchAccount");
    private final static QName _ThrowInvalidInput_QNAME = new QName("http://exceptionthrower.development.soap.services.server.exampleproject.soapworks.optimaize.com/", "throwInvalidInput");
    private final static QName _InvalidInputWebServiceException_QNAME = new QName("http://exceptionthrower.development.soap.services.server.exampleproject.soapworks.optimaize.com/", "InvalidInputWebServiceException");
    private final static QName _InternalServerErrorWebServiceException_QNAME = new QName("http://exceptionthrower.development.soap.services.server.exampleproject.soapworks.optimaize.com/", "InternalServerErrorWebServiceException");
    private final static QName _ThrowInvalidInputResponse_QNAME = new QName("http://exceptionthrower.development.soap.services.server.exampleproject.soapworks.optimaize.com/", "throwInvalidInputResponse");
    private final static QName _ThrowAccessDeniedTooManyConcurrentRequests_QNAME = new QName("http://exceptionthrower.development.soap.services.server.exampleproject.soapworks.optimaize.com/", "throwAccessDeniedTooManyConcurrentRequests");
    private final static QName _ThrowInternal_QNAME = new QName("http://exceptionthrower.development.soap.services.server.exampleproject.soapworks.optimaize.com/", "throwInternal");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.optimaize.soapworks.exampleproject.clientlib.services.system.exceptionthrower.wsdl
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
     * Create an instance of {@link ThrowAccessDeniedNoSuchAccountResponse }
     * 
     */
    public ThrowAccessDeniedNoSuchAccountResponse createThrowAccessDeniedNoSuchAccountResponse() {
        return new ThrowAccessDeniedNoSuchAccountResponse();
    }

    /**
     * Create an instance of {@link ThrowAccessDeniedRequestLimitExceeded }
     * 
     */
    public ThrowAccessDeniedRequestLimitExceeded createThrowAccessDeniedRequestLimitExceeded() {
        return new ThrowAccessDeniedRequestLimitExceeded();
    }

    /**
     * Create an instance of {@link ThrowAccessDeniedRequestLimitExceededResponse }
     * 
     */
    public ThrowAccessDeniedRequestLimitExceededResponse createThrowAccessDeniedRequestLimitExceededResponse() {
        return new ThrowAccessDeniedRequestLimitExceededResponse();
    }

    /**
     * Create an instance of {@link ThrowInternalResponse }
     * 
     */
    public ThrowInternalResponse createThrowInternalResponse() {
        return new ThrowInternalResponse();
    }

    /**
     * Create an instance of {@link ThrowAccessDeniedTooManyConcurrentRequestsResponse }
     * 
     */
    public ThrowAccessDeniedTooManyConcurrentRequestsResponse createThrowAccessDeniedTooManyConcurrentRequestsResponse() {
        return new ThrowAccessDeniedTooManyConcurrentRequestsResponse();
    }

    /**
     * Create an instance of {@link ThrowInternal }
     * 
     */
    public ThrowInternal createThrowInternal() {
        return new ThrowInternal();
    }

    /**
     * Create an instance of {@link ThrowInvalidInputResponse }
     * 
     */
    public ThrowInvalidInputResponse createThrowInvalidInputResponse() {
        return new ThrowInvalidInputResponse();
    }

    /**
     * Create an instance of {@link ThrowAccessDeniedTooManyConcurrentRequests }
     * 
     */
    public ThrowAccessDeniedTooManyConcurrentRequests createThrowAccessDeniedTooManyConcurrentRequests() {
        return new ThrowAccessDeniedTooManyConcurrentRequests();
    }

    /**
     * Create an instance of {@link ThrowInvalidInput }
     * 
     */
    public ThrowInvalidInput createThrowInvalidInput() {
        return new ThrowInvalidInput();
    }

    /**
     * Create an instance of {@link ThrowAccessDeniedNoSuchAccount }
     * 
     */
    public ThrowAccessDeniedNoSuchAccount createThrowAccessDeniedNoSuchAccount() {
        return new ThrowAccessDeniedNoSuchAccount();
    }

    /**
     * Create an instance of {@link Retry }
     * 
     */
    public Retry createRetry() {
        return new Retry();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ThrowAccessDeniedRequestLimitExceeded }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://exceptionthrower.development.soap.services.server.exampleproject.soapworks.optimaize.com/", name = "throwAccessDeniedRequestLimitExceeded")
    public JAXBElement<ThrowAccessDeniedRequestLimitExceeded> createThrowAccessDeniedRequestLimitExceeded(ThrowAccessDeniedRequestLimitExceeded value) {
        return new JAXBElement<ThrowAccessDeniedRequestLimitExceeded>(_ThrowAccessDeniedRequestLimitExceeded_QNAME, ThrowAccessDeniedRequestLimitExceeded.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ThrowAccessDeniedRequestLimitExceededResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://exceptionthrower.development.soap.services.server.exampleproject.soapworks.optimaize.com/", name = "throwAccessDeniedRequestLimitExceededResponse")
    public JAXBElement<ThrowAccessDeniedRequestLimitExceededResponse> createThrowAccessDeniedRequestLimitExceededResponse(ThrowAccessDeniedRequestLimitExceededResponse value) {
        return new JAXBElement<ThrowAccessDeniedRequestLimitExceededResponse>(_ThrowAccessDeniedRequestLimitExceededResponse_QNAME, ThrowAccessDeniedRequestLimitExceededResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ThrowInternalResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://exceptionthrower.development.soap.services.server.exampleproject.soapworks.optimaize.com/", name = "throwInternalResponse")
    public JAXBElement<ThrowInternalResponse> createThrowInternalResponse(ThrowInternalResponse value) {
        return new JAXBElement<ThrowInternalResponse>(_ThrowInternalResponse_QNAME, ThrowInternalResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ThrowAccessDeniedTooManyConcurrentRequestsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://exceptionthrower.development.soap.services.server.exampleproject.soapworks.optimaize.com/", name = "throwAccessDeniedTooManyConcurrentRequestsResponse")
    public JAXBElement<ThrowAccessDeniedTooManyConcurrentRequestsResponse> createThrowAccessDeniedTooManyConcurrentRequestsResponse(ThrowAccessDeniedTooManyConcurrentRequestsResponse value) {
        return new JAXBElement<ThrowAccessDeniedTooManyConcurrentRequestsResponse>(_ThrowAccessDeniedTooManyConcurrentRequestsResponse_QNAME, ThrowAccessDeniedTooManyConcurrentRequestsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ThrowAccessDeniedNoSuchAccountResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://exceptionthrower.development.soap.services.server.exampleproject.soapworks.optimaize.com/", name = "throwAccessDeniedNoSuchAccountResponse")
    public JAXBElement<ThrowAccessDeniedNoSuchAccountResponse> createThrowAccessDeniedNoSuchAccountResponse(ThrowAccessDeniedNoSuchAccountResponse value) {
        return new JAXBElement<ThrowAccessDeniedNoSuchAccountResponse>(_ThrowAccessDeniedNoSuchAccountResponse_QNAME, ThrowAccessDeniedNoSuchAccountResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FaultBean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://exceptionthrower.development.soap.services.server.exampleproject.soapworks.optimaize.com/", name = "AccessDeniedWebServiceException")
    public JAXBElement<FaultBean> createAccessDeniedWebServiceException(FaultBean value) {
        return new JAXBElement<FaultBean>(_AccessDeniedWebServiceException_QNAME, FaultBean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ThrowAccessDeniedNoSuchAccount }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://exceptionthrower.development.soap.services.server.exampleproject.soapworks.optimaize.com/", name = "throwAccessDeniedNoSuchAccount")
    public JAXBElement<ThrowAccessDeniedNoSuchAccount> createThrowAccessDeniedNoSuchAccount(ThrowAccessDeniedNoSuchAccount value) {
        return new JAXBElement<ThrowAccessDeniedNoSuchAccount>(_ThrowAccessDeniedNoSuchAccount_QNAME, ThrowAccessDeniedNoSuchAccount.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ThrowInvalidInput }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://exceptionthrower.development.soap.services.server.exampleproject.soapworks.optimaize.com/", name = "throwInvalidInput")
    public JAXBElement<ThrowInvalidInput> createThrowInvalidInput(ThrowInvalidInput value) {
        return new JAXBElement<ThrowInvalidInput>(_ThrowInvalidInput_QNAME, ThrowInvalidInput.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FaultBean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://exceptionthrower.development.soap.services.server.exampleproject.soapworks.optimaize.com/", name = "InvalidInputWebServiceException")
    public JAXBElement<FaultBean> createInvalidInputWebServiceException(FaultBean value) {
        return new JAXBElement<FaultBean>(_InvalidInputWebServiceException_QNAME, FaultBean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FaultBean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://exceptionthrower.development.soap.services.server.exampleproject.soapworks.optimaize.com/", name = "InternalServerErrorWebServiceException")
    public JAXBElement<FaultBean> createInternalServerErrorWebServiceException(FaultBean value) {
        return new JAXBElement<FaultBean>(_InternalServerErrorWebServiceException_QNAME, FaultBean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ThrowInvalidInputResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://exceptionthrower.development.soap.services.server.exampleproject.soapworks.optimaize.com/", name = "throwInvalidInputResponse")
    public JAXBElement<ThrowInvalidInputResponse> createThrowInvalidInputResponse(ThrowInvalidInputResponse value) {
        return new JAXBElement<ThrowInvalidInputResponse>(_ThrowInvalidInputResponse_QNAME, ThrowInvalidInputResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ThrowAccessDeniedTooManyConcurrentRequests }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://exceptionthrower.development.soap.services.server.exampleproject.soapworks.optimaize.com/", name = "throwAccessDeniedTooManyConcurrentRequests")
    public JAXBElement<ThrowAccessDeniedTooManyConcurrentRequests> createThrowAccessDeniedTooManyConcurrentRequests(ThrowAccessDeniedTooManyConcurrentRequests value) {
        return new JAXBElement<ThrowAccessDeniedTooManyConcurrentRequests>(_ThrowAccessDeniedTooManyConcurrentRequests_QNAME, ThrowAccessDeniedTooManyConcurrentRequests.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ThrowInternal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://exceptionthrower.development.soap.services.server.exampleproject.soapworks.optimaize.com/", name = "throwInternal")
    public JAXBElement<ThrowInternal> createThrowInternal(ThrowInternal value) {
        return new JAXBElement<ThrowInternal>(_ThrowInternal_QNAME, ThrowInternal.class, null, value);
    }

}
