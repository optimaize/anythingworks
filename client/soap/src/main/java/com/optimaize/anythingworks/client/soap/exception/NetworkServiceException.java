package com.optimaize.anythingworks.client.soap.exception;

import org.jetbrains.annotations.NotNull;

/**
 * Base class for exceptions thrown by web services where the Network (not the Client nor Server) is to blame.
 *
 * <p>Things like these: http://msdn.microsoft.com/en-us/library/system.net.webexceptionstatus.aspx</p>
 *
 * <p>Network exceptions can be thrown by the client only.</p>
 *
 * @author fab
 */
public abstract class NetworkServiceException extends ServiceException {

    public NetworkServiceException(@NotNull FaultInfo faultInfo) {
        super(faultInfo);
    }

    public NetworkServiceException(String message, @NotNull FaultInfo faultInfo) {
        super(message, faultInfo);
    }

    public NetworkServiceException(String message, Throwable cause, @NotNull FaultInfo faultInfo) {
        super(message, cause, faultInfo);
    }

    public NetworkServiceException(Throwable cause, @NotNull FaultInfo faultInfo) {
        super(cause, faultInfo);
    }
}
