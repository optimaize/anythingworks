package com.optimaize.soapworks.server.soap.exception;

import org.jetbrains.annotations.NotNull;

import javax.xml.ws.WebFault;

/**
 * Thrown to inform that the service is temporarily unavailable.
 *
 * <p>In HTTP Status Code terms this can mean:
 *   <li>503 Service Unavailable</li>
 *   <li>504 Gateway Timeout</li>
 * </p>
 *
 * <p>Possible causes:
 * <ul>
 *   <li>maintenance time</li>
 *   <li>temporary overload</li>
 *   <li>service aborted and has not come back yet</li>
 * </ul></p>
 *
 * If there is no such service, or the service is gone for good, then a {@link NoSuchServiceWebServiceException}
 * is thrown instead (client error, not server error).
 *
 * @author Fabian Kessler
 */
@WebFault(name="ServiceUnavailableWebServiceException")
public class ServiceUnavailableWebServiceException extends ServerWebServiceException {

    private static final long serialVersionUID = 1L;

    private static final String FAULT_CAUSE = "ServiceUnavailable";
    private static final String defaultMessage = "Service unavailable";


    public ServiceUnavailableWebServiceException(@NotNull FaultBean faultBean, Throwable cause) {
        //TODO stick SoapFaultCode.Client into cause (dunno how)
        super(faultBean, cause);
    }
    public ServiceUnavailableWebServiceException(@NotNull FaultBean faultBean) {
        super(faultBean);
    }

    public ServiceUnavailableWebServiceException() {
        super(FaultBeans.Server.ServiceTemporarilyUnavailable.serviceTemporarilyUnavailable());
    }


//    public ServiceUnavailableWebServiceException(@NotNull Retry retryThisServer, boolean problemReported) {
//        this(defaultMessage, retryThisServer, problemReported);
//    }
//    public ServiceUnavailableWebServiceException(@NotNull String message, @NotNull Retry retryThisServer, boolean problemReported) {
//        super(new FaultBean(1500, BLAME, FAULT_CAUSE, message, retryThisServer, Retry.now(), problemReported));
//    }
//    public ServiceUnavailableWebServiceException(@NotNull Throwable cause, @NotNull Retry retryThisServer, boolean problemReported) {
//        this(defaultMessage, cause, retryThisServer, problemReported);
//    }
//    public ServiceUnavailableWebServiceException(@NotNull String message, @NotNull Throwable cause, @NotNull Retry retryThisServer, boolean problemReported) {
//        super(new FaultBean(1500, BLAME, FAULT_CAUSE, message, retryThisServer, Retry.now(), problemReported), cause);
//    }

}
