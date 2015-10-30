package com.optimaize.soapworks.client.soap.exception;

import org.jetbrains.annotations.NotNull;

/**
 * Base class for all exceptions that may be thrown from service methods.
 *
 * @author fab
 */
public abstract class ServiceException extends RuntimeException {

    @NotNull
    private final FaultInfo faultInfo;


    public ServiceException(@NotNull FaultInfo faultInfo) {
        this.faultInfo = faultInfo;
    }
    public ServiceException(String message, @NotNull FaultInfo faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }
    public ServiceException(String message, Throwable cause, @NotNull FaultInfo faultInfo) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }
    public ServiceException(Throwable cause, @NotNull FaultInfo faultInfo) {
        super(cause);
        this.faultInfo = faultInfo;
    }


    @NotNull
    public FaultInfo getFaultInfo() {
        return faultInfo;
    }
}
