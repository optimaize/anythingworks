package com.optimaize.soapworks.client.exception;

import org.jetbrains.annotations.NotNull;

/**
 * Base class for exceptions thrown by web services where the Server (not the Client) is to blame.
 *
 * <p>Server exceptions can be thrown by the server only.</p>
 *
 * @author fab
 */
public abstract class ServerServiceException extends ServiceException {

    public ServerServiceException(@NotNull FaultInfo faultInfo) {
        super(faultInfo);
    }

    public ServerServiceException(String message, @NotNull FaultInfo faultInfo) {
        super(message, faultInfo);
    }

    public ServerServiceException(String message, Throwable cause, @NotNull FaultInfo faultInfo) {
        super(message, cause, faultInfo);
    }

    public ServerServiceException(Throwable cause, @NotNull FaultInfo faultInfo) {
        super(cause, faultInfo);
    }
}
