package com.optimaize.soapworks.server.exception;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPFault;
import javax.xml.ws.soap.SOAPFaultException;

/**
 * Base class for exceptions thrown by web services.
 * @author Fabian Kessler
 */
public abstract class BaseWebServiceException extends Exception {

    private static final long serialVersionUID = 1L;

    @NotNull
    private FaultBean faultBean;

    public BaseWebServiceException(@NotNull FaultBean faultBean, @Nullable Throwable cause) {
//        super(message, wrapCause(cause));
        super(faultBean.getMessage(), cause);
        this.faultBean = faultBean;
    }
    public BaseWebServiceException(@NotNull FaultBean faultBean) {
        this(faultBean, null);
    }


//    private static Throwable wrapCause(Throwable cause) {
//        SOAPFaultException sfe = createSfe(cause);
//        if (sfe == null) return cause;
//        sfe.s
//    }
    @Nullable
    protected static SOAPFaultException createSfe(@NotNull SoapFaultCode soapFaultCode) {
        try {
            QName qname = new QName("http://schemas.xmlsoap.org/soap/envelope/", soapFaultCode.name());
            SOAPFactory sf = SOAPFactory.newInstance();
            SOAPFault fault = sf.createFault("", qname);
            return new SOAPFaultException(fault);
        } catch (SOAPException e) {
            //e.printStackTrace();
            return null;
        }
    }

    @NotNull
    public FaultBean getFaultInfo() {
        return faultBean;
    }

}
