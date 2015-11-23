package com.optimaize.anythingworks.common.fault.exceptions;

import com.optimaize.anythingworks.common.fault.faultinfo.FaultInfo;
import org.jetbrains.annotations.NotNull;

/**
 * Base class for all exceptions that may be thrown from service methods.
 *
 * @author fab
 */
public class ServiceException extends RuntimeException {

    @NotNull
    private final FaultInfo faultInfo;


    public ServiceException(@NotNull FaultInfo faultInfo) {
        this.faultInfo = faultInfo;
    }

    public ServiceException(@NotNull FaultInfo faultInfo, Throwable throwable) {
        super(throwable);
        this.faultInfo = faultInfo;
    }

    @Override
    public String getMessage() {
        return faultInfo.getMessage();
    }

    @NotNull
    public FaultInfo getFaultInfo() {
        return faultInfo;
    }
}
