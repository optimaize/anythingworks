package com.optimaize.anythingworks.server.rest.fault.exception;

import com.optimaize.anythingworks.common.fault.exceptions.BadRequestServiceException;
import com.optimaize.anythingworks.common.fault.exceptions.InternalServerErrorServiceException;
import com.optimaize.anythingworks.server.common.fault.FaultInfos;
import com.optimaize.anythingworks.server.rest.fault.RestFaultInfoBuilder;
import org.jetbrains.annotations.NotNull;

import javax.ws.rs.core.Response;

/**
 *
 */
public class InternalServerErrorServiceExceptions {

    /**
     * @return never returns.
     */
    public static InternalServerErrorServiceException internalServerError(@NotNull String message) throws BadRequestServiceException {
        throw new InternalServerErrorServiceException(
                RestFaultInfoBuilder.faultInfo(
                        FaultInfos.Server.InternalServerError.internalServerError(message),
                        Response.Status.INTERNAL_SERVER_ERROR
                )
        );
    }


}
