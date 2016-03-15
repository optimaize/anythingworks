
package com.optimaize.anythingworks.exampleproject.clientlib.soap.services.system.pingv2.wsdl;

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
@WebServiceClient(name = "V2SoapPingServiceService", targetNamespace = "http://ping.system.soap.services.server.exampleproject.anythingworks.optimaize.com/", wsdlLocation = "http://localhost/soap/v2/system/ping?wsdl")
public class V2SoapPingServiceService
    extends Service
{

    private final static URL V2SOAPPINGSERVICESERVICE_WSDL_LOCATION;
    private final static WebServiceException V2SOAPPINGSERVICESERVICE_EXCEPTION;
    private final static QName V2SOAPPINGSERVICESERVICE_QNAME = new QName("http://ping.system.soap.services.server.exampleproject.anythingworks.optimaize.com/", "V2SoapPingServiceService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost/soap/v2/system/ping?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        V2SOAPPINGSERVICESERVICE_WSDL_LOCATION = url;
        V2SOAPPINGSERVICESERVICE_EXCEPTION = e;
    }

    public V2SoapPingServiceService() {
        super(__getWsdlLocation(), V2SOAPPINGSERVICESERVICE_QNAME);
    }

    public V2SoapPingServiceService(WebServiceFeature... features) {
        super(__getWsdlLocation(), V2SOAPPINGSERVICESERVICE_QNAME, features);
    }

    public V2SoapPingServiceService(URL wsdlLocation) {
        super(wsdlLocation, V2SOAPPINGSERVICESERVICE_QNAME);
    }

    public V2SoapPingServiceService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, V2SOAPPINGSERVICESERVICE_QNAME, features);
    }

    public V2SoapPingServiceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public V2SoapPingServiceService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns V2SoapPingService
     */
    @WebEndpoint(name = "V2SoapPingServicePort")
    public V2SoapPingService getV2SoapPingServicePort() {
        return super.getPort(new QName("http://ping.system.soap.services.server.exampleproject.anythingworks.optimaize.com/", "V2SoapPingServicePort"), V2SoapPingService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns V2SoapPingService
     */
    @WebEndpoint(name = "V2SoapPingServicePort")
    public V2SoapPingService getV2SoapPingServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://ping.system.soap.services.server.exampleproject.anythingworks.optimaize.com/", "V2SoapPingServicePort"), V2SoapPingService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (V2SOAPPINGSERVICESERVICE_EXCEPTION!= null) {
            throw V2SOAPPINGSERVICESERVICE_EXCEPTION;
        }
        return V2SOAPPINGSERVICESERVICE_WSDL_LOCATION;
    }

}
