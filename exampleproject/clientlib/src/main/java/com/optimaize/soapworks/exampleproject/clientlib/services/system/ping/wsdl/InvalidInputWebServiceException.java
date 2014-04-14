
package com.optimaize.soapworks.exampleproject.clientlib.services.system.ping.wsdl;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "InvalidInputWebServiceException", targetNamespace = "http://system.services.server.exampleproject.soapworks.optimaize.com/")
public class InvalidInputWebServiceException
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private FaultBean faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public InvalidInputWebServiceException(String message, FaultBean faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public InvalidInputWebServiceException(String message, FaultBean faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.optimaize.soapworks.exampleproject.clientlib.services.system.ping.wsdl.FaultBean
     */
    public FaultBean getFaultInfo() {
        return faultInfo;
    }

}
