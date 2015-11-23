package com.optimaize.anythingworks.server.rest.fault.exception;

import com.optimaize.anythingworks.common.fault.exceptions.BadRequestServiceException;
import com.optimaize.anythingworks.common.fault.exceptions.InternalServerErrorServiceException;
import com.optimaize.anythingworks.common.fault.exceptions.ServiceTemporarilyUnavailableServiceException;
import com.optimaize.anythingworks.server.common.fault.FaultInfos;
import com.optimaize.anythingworks.server.rest.fault.RestFaultInfoBuilder;
import org.jetbrains.annotations.NotNull;

import javax.ws.rs.core.Response;

/**
 *
 */
public class ServiceTemporarilyUnavailableServiceExceptions {

    /**
     * @return never returns.
     */
    public static ServiceTemporarilyUnavailableServiceException serviceTemporarilyUnavailable() throws BadRequestServiceException {
        throw new ServiceTemporarilyUnavailableServiceException(
                RestFaultInfoBuilder.faultInfo(
                        FaultInfos.Server.ServiceTemporarilyUnavailable.serviceTemporarilyUnavailable(),
                        Response.Status.SERVICE_UNAVAILABLE
                )
        );
    }


}
