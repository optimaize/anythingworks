package com.optimaize.soapworks.exception;

import org.jetbrains.annotations.NotNull;

import javax.xml.ws.WebFault;

/**
 * Thrown when there is no such service available.
 *
 * <p>In HTTP Status Code terms this can mean:
 *   <li>404 Not Found</li>
 *   <li>410 Gone</li>
 *   <li>301 Moved Permanently</li>
 * </p>
 *
 * <p>It can be that the service exists but not for the requested version number, or that the
 * requested version number has no such service, or both are unknown.</p>
 *
 * <p>Another server location may have that service. It's possible that just this location
 * does not have it, for example because it's an older deprecated version and not all
 * servers are running it anymore. Or it's a new beta version only running on one server.</p>
 */
@WebFault(name="NoSuchServiceWebServiceException")
public class NoSuchServiceWebServiceException extends ClientWebServiceException {

    private static final long serialVersionUID = 1L;

    private static final String FAULT_CAUSE = "NoSuchService";
    private static final SoapFaultCode SOAP_FAULT_CODE = SoapFaultCode.Client;


    public NoSuchServiceWebServiceException(@NotNull FaultBean faultBean, Throwable cause) {
        super(faultBean, cause);
    }
    public NoSuchServiceWebServiceException(@NotNull FaultBean faultBean) {
        super(faultBean);
    }

    public NoSuchServiceWebServiceException(String message) {
        this(message, createSfe(SoapFaultCode.Client));
    }

    public NoSuchServiceWebServiceException(String message, Throwable cause) {
        //TODO stick SoapFaultCode.Client into cause (dunno how)
        super(new FaultBean(2300, Blame.CLIENT, FAULT_CAUSE, message, Retry.no(), Retry.no(), PROBLEM_REPORTED), cause);
    }

    public NoSuchServiceWebServiceException(Throwable cause) {
        this(cause.getMessage(), cause);
    }

}
