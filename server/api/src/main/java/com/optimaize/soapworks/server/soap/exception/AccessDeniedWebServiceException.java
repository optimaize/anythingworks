package com.optimaize.soapworks.server.soap.exception;

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

    private static final SoapFaultCode SOAP_FAULT_CODE = SoapFaultCode.Client;


    /**
     * @return never returns.
     */
    public static AccessDeniedWebServiceException accountUnknown(@NotNull String userId) throws AccessDeniedWebServiceException {
        throw new AccessDeniedWebServiceException(
                FaultBeans.Client.AccessDenied.accountUnknown(userId),
                createSfe(SOAP_FAULT_CODE)
        );
    }

    /**
     * @return never returns.
     */
    public static AccessDeniedWebServiceException accountInactive(@NotNull String userId) throws AccessDeniedWebServiceException {
        throw new AccessDeniedWebServiceException(
                FaultBeans.Client.AccessDenied.accountInactive(userId),
                createSfe(SOAP_FAULT_CODE)
        );
    }
    /**
     * @return never returns.
     */
    public static AccessDeniedWebServiceException accountInactive(@NotNull String userId, @NotNull String comment) throws AccessDeniedWebServiceException {
        throw new AccessDeniedWebServiceException(
                FaultBeans.Client.AccessDenied.accountInactive(userId, comment),
                createSfe(SOAP_FAULT_CODE)
        );
    }

    /**
     * @return never returns.
     */
    public static AccessDeniedWebServiceException networkRestriction(@NotNull String userId, @NotNull String hostOrIp) throws AccessDeniedWebServiceException {
        throw new AccessDeniedWebServiceException(
                FaultBeans.Client.AccessDenied.networkRestriction(userId, hostOrIp),
                createSfe(SOAP_FAULT_CODE)
        );
    }

    /**
     * @return never returns.
     */
    public static AccessDeniedWebServiceException requestLimitExceeded(@NotNull String userId, @NotNull String timePeriod) throws AccessDeniedWebServiceException {
        throw new AccessDeniedWebServiceException(
                FaultBeans.Client.AccessDenied.requestLimitReached(userId, timePeriod),
                createSfe(SOAP_FAULT_CODE)
        );
    }

    /**
     * @return never returns.
     */
    public static AccessDeniedWebServiceException tooManyConcurrentRequests(@NotNull String userId) throws AccessDeniedWebServiceException {
        throw new AccessDeniedWebServiceException(
                FaultBeans.Client.AccessDenied.tooManyConcurrentRequests(userId),
                createSfe(SOAP_FAULT_CODE)
        );
    }

    /**
     * @return never returns.
     */
    public static AccessDeniedWebServiceException hostBlocked(@NotNull String message) throws AccessDeniedWebServiceException {
        throw new AccessDeniedWebServiceException(
                FaultBeans.Client.AccessDenied.hostBlocked(message),
                createSfe(SOAP_FAULT_CODE)
        );
    }

    /**
     * @return never returns.
     */
    public static AccessDeniedWebServiceException other(@NotNull FaultBean faultBean) throws AccessDeniedWebServiceException {
        throw new AccessDeniedWebServiceException(faultBean);
    }

    public AccessDeniedWebServiceException(@NotNull FaultBean faultBean, Throwable cause) {
        super(faultBean, cause);
    }

    public AccessDeniedWebServiceException(@NotNull FaultBean faultBean) {
        super(faultBean);
    }

}
