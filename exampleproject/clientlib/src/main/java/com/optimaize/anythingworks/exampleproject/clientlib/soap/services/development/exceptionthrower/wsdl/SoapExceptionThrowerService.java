
package com.optimaize.anythingworks.exampleproject.clientlib.soap.services.development.exceptionthrower.wsdl;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "SoapExceptionThrowerService", targetNamespace = "http://exceptionthrower.development.soap.services.server.exampleproject.anythingworks.optimaize.com/", wsdlLocation = "http://localhost/soap/v1/development/exceptionthrower?wsdl")
public class SoapExceptionThrowerService
    extends Service
{

    private final static URL SOAPEXCEPTIONTHROWERSERVICE_WSDL_LOCATION;
    private final static WebServiceException SOAPEXCEPTIONTHROWERSERVICE_EXCEPTION;
    private final static QName SOAPEXCEPTIONTHROWERSERVICE_QNAME = new QName("http://exceptionthrower.development.soap.services.server.exampleproject.anythingworks.optimaize.com/", "SoapExceptionThrowerService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost/soap/v1/development/exceptionthrower?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        SOAPEXCEPTIONTHROWERSERVICE_WSDL_LOCATION = url;
        SOAPEXCEPTIONTHROWERSERVICE_EXCEPTION = e;
    }

    public SoapExceptionThrowerService() {
        super(__getWsdlLocation(), SOAPEXCEPTIONTHROWERSERVICE_QNAME);
    }

    public SoapExceptionThrowerService(WebServiceFeature... features) {
        super(__getWsdlLocation(), SOAPEXCEPTIONTHROWERSERVICE_QNAME, features);
    }

    public SoapExceptionThrowerService(URL wsdlLocation) {
        super(wsdlLocation, SOAPEXCEPTIONTHROWERSERVICE_QNAME);
    }

    public SoapExceptionThrowerService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SOAPEXCEPTIONTHROWERSERVICE_QNAME, features);
    }

    public SoapExceptionThrowerService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SoapExceptionThrowerService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns SoapExceptionThrower
     */
    @WebEndpoint(name = "SoapExceptionThrowerPort")
    public SoapExceptionThrower getSoapExceptionThrowerPort() {
        return super.getPort(new QName("http://exceptionthrower.development.soap.services.server.exampleproject.anythingworks.optimaize.com/", "SoapExceptionThrowerPort"), SoapExceptionThrower.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SoapExceptionThrower
     */
    @WebEndpoint(name = "SoapExceptionThrowerPort")
    public SoapExceptionThrower getSoapExceptionThrowerPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://exceptionthrower.development.soap.services.server.exampleproject.anythingworks.optimaize.com/", "SoapExceptionThrowerPort"), SoapExceptionThrower.class, features);
    }

    private static URL __getWsdlLocation() {
        if (SOAPEXCEPTIONTHROWERSERVICE_EXCEPTION!= null) {
            throw SOAPEXCEPTIONTHROWERSERVICE_EXCEPTION;
        }
        return SOAPEXCEPTIONTHROWERSERVICE_WSDL_LOCATION;
    }

}
