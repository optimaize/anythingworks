package com.optimaize.anythingworks.server.rest.fault.exception;

import com.optimaize.anythingworks.common.fault.exceptions.BadRequestServiceException;
import com.optimaize.anythingworks.server.common.fault.FaultInfos;
import com.optimaize.anythingworks.server.rest.fault.RestFaultInfoBuilder;
import org.jetbrains.annotations.NotNull;

import javax.ws.rs.core.Response;

/**
 *
 */
public class BadRequestServiceExceptions {

    /**
     * @return never returns.
     */
    public static BadRequestServiceException invalidInput(@NotNull String message) throws BadRequestServiceException {
        throw new BadRequestServiceException(
                RestFaultInfoBuilder.faultInfo(
                        FaultInfos.Client.BadRequest.invalidInput(message),
                        Response.Status.BAD_REQUEST
                )
        );
    }


}
