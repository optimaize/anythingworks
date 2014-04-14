package com.optimaize.soapworks.client.exception;

import org.jetbrains.annotations.NotNull;

/**
 * Thrown when this client library has no mapping for the exception that the server threw.
 * This should not happen, but can.
 *
 * <p>Possible causes:
 * <ul>
 *   <li>client was not updated when server got new features</li>
 *   <li>a bug</li>
 * </ul></p>
 *
 * @author fab
 */
public class UnknownServiceException extends ServiceException {

//    public AccessDeniedServiceException(@NotNull FaultInfo faultInfo) {
//        super(faultInfo);
//    }

    public UnknownServiceException(String message, @NotNull FaultInfo faultInfo) {
        super(message, faultInfo);
    }

//    public AccessDeniedServiceException(String message, Throwable cause, @NotNull FaultInfo faultInfo) {
//        super(message, cause, faultInfo);
//    }
//
//    public AccessDeniedServiceException(Throwable cause, @NotNull FaultInfo faultInfo) {
//        super(cause, faultInfo);
//    }

}
