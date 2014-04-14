package com.optimaize.soapworks.client.exception;

import org.jetbrains.annotations.NotNull;

/**
 * Base class for exceptions thrown by web services where the Client (not the Server) is to blame.
 *
 * <p>Client exceptions can be thrown by the server and the client.</p>
 *
 * @author fab
 */
public abstract class ClientServiceException extends ServiceException {

    public ClientServiceException(@NotNull FaultInfo faultInfo) {
        super(faultInfo);
    }

    public ClientServiceException(String message, @NotNull FaultInfo faultInfo) {
        super(message, faultInfo);
    }

    public ClientServiceException(String message, Throwable cause, @NotNull FaultInfo faultInfo) {
        super(message, cause, faultInfo);
    }

    public ClientServiceException(Throwable cause, @NotNull FaultInfo faultInfo) {
        super(cause, faultInfo);
    }
}
