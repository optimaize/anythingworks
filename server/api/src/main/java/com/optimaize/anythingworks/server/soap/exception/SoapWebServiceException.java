package com.optimaize.anythingworks.server.soap.exception;

import com.optimaize.anythingworks.common.fault.faultinfo.FaultInfo;
import com.optimaize.anythingworks.common.fault.faultinfo.Retry;
import com.optimaize.anythingworks.server.common.exception.FaultBean;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFactory;
import javax.xml.soap.SOAPFault;
import javax.xml.ws.WebFault;
import javax.xml.ws.soap.SOAPFaultException;

/**
 * Exception class for exceptions thrown by web services using the SOAP protocol.
 *
 * To keep it simple, there is just this one exception. The detail of the cause is in the FaultBean.
 *
 * @author Fabian Kessler
 */
@WebFault
public class SoapWebServiceException extends Exception {

    private static final long serialVersionUID = 1L;

    @NotNull
    private FaultBean faultBean;

    public SoapWebServiceException(@NotNull FaultInfo faultInfo, @Nullable Throwable cause) {
        super(faultInfo.getMessage(), cause);
        this.faultBean = convert(faultInfo);
    }
    public SoapWebServiceException(@NotNull FaultInfo faultInfo) {
        this(faultInfo, null);
    }
    private static FaultBean convert(FaultInfo faultInfo) {
        return new FaultBean(
                faultInfo.getFaultCause(),
                faultInfo.getBlame(),
                faultInfo.getMessage(),
                faultInfo.getApplicationErrorCode().orNull(),
                faultInfo.getIncidentId().orNull(),
                convert(faultInfo.getRetrySameLocation().orNull()),
                convert(faultInfo.getRetryOtherLocations().orNull())
        );
    }
    private static com.optimaize.anythingworks.server.common.exception.Retry convert(Retry retry) {
        if (retry==null) return null;
        return new com.optimaize.anythingworks.server.common.exception.Retry(
                retry.getRetryType(),
                retry.getRetryInSeconds().orNull()
        );
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

    /**
     * According to the SOAP spec there must be a method with the name "getFaultInfo".
     */
    @NotNull
    public FaultBean getFaultInfo() {
        return faultBean;
    }

}
