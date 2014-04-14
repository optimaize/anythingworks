package com.optimaize.soapworks.exception;

import org.jetbrains.annotations.NotNull;
import javax.xml.ws.WebFault;

/**
 * Thrown when the server denies access to the client.
 *
 * <p>Possible causes:
 * <ul>
 *   <li>invalid credentials</li>
 *   <li>account inactive, disabled, deleted</li>
 *   <li>out of credits</li>
 * </ul></p>
 *
 * <p>In HTTP Status Code terms this can mean:
 * <ul>
 *   <li>401 Unauthorized</li>
 *   <li>403 Forbidden</li>
 * </ul></p>
 */
@WebFault //(name="AccessDeniedWebServiceException")
public class AccessDeniedWebServiceException extends ClientWebServiceException {

    private static final long serialVersionUID = 1L;

    private static final String FAULT_CAUSE = "AccessDenied";
    private static final SoapFaultCode SOAP_FAULT_CODE = SoapFaultCode.Client;


    /**
     * @return never returns.
     */
    public static AccessDeniedWebServiceException noSuchAccount(@NotNull String userId) throws AccessDeniedWebServiceException {
        throw new AccessDeniedWebServiceException(
                faultBean(2101, "No such account: >>>" + userId + "<<<!", Retry.no()),
                createSfe(SOAP_FAULT_CODE)
        );
    }

    /**
     * @return never returns.
     */
    public static AccessDeniedWebServiceException requestLimitExceeded() throws AccessDeniedWebServiceException {
        throw new AccessDeniedWebServiceException(
                faultBean(2120, "Request limit exceeded!", Retry.later(null))
        );
    }
    /**
     * @return never returns.
     */
    public static AccessDeniedWebServiceException tooManyConcurrentRequests() throws AccessDeniedWebServiceException {
        throw new AccessDeniedWebServiceException(
                faultBean(2121, "Too many concurrent requests!", Retry.later(null))
        );
    }

    public AccessDeniedWebServiceException(@NotNull FaultBean faultBean, Throwable cause) {
        super(faultBean, cause);
    }

    public AccessDeniedWebServiceException(@NotNull FaultBean faultBean) {
        super(faultBean);
    }

    private static FaultBean faultBean(int errorCode, String message, Retry retry) {
        return new FaultBean(errorCode, Blame.CLIENT, FAULT_CAUSE, message, retry, retry, PROBLEM_REPORTED);
    }

}
