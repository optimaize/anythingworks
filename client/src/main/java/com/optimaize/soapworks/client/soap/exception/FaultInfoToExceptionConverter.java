package com.optimaize.soapworks.client.soap.exception;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class FaultInfoToExceptionConverter {

    private static final Logger log = LoggerFactory.getLogger(FaultInfoToExceptionConverter.class);



    @NotNull
    public ServiceException makeException(@Nullable String msg, @NotNull FaultInfo faultInfo) {
        String className = faultInfo.getFaultCause()+"WebServiceException";
        switch (className) {
            case "AccessDeniedWebServiceException":
                return new AccessDeniedServiceException(msg, faultInfo);
            case "InvalidInputWebServiceException":
                return new InvalidInputServiceException(msg, faultInfo);
            case "InternalServerErrorWebServiceException":
            case "InternalWebServiceException": //old name
                return new InternalServiceException(msg, faultInfo);
            default:
                log.error("Unknown exception type from server: ");
                return new UnknownServiceException(msg, faultInfo);
        }
    }

    @NotNull
    public ServiceException makeException(@NotNull FaultInfo faultInfo) {
        return makeException(faultInfo.getMessage(), faultInfo);
    }


}
