package com.optimaize.anythingworks.exampleproject.server.lib;

import com.google.common.base.Optional;
import com.optimaize.anythingworks.common.fault.ErrorCodes;
import com.optimaize.anythingworks.common.fault.exceptions.ServiceException;
import com.optimaize.anythingworks.server.common.fault.FaultInfoBuilders;
import com.optimaize.anythingworks.server.rest.FaultInfoEnvelope;
import com.optimaize.anythingworks.server.rest.RestWebService;
import com.optimaize.anythingworks.server.rest.fault.exception.InternalServerErrorServiceExceptions;
import com.optimaize.anythingworks.server.soap.SoapWebService;
import com.optimaize.anythingworks.server.soap.exception.SoapWebServiceException;
import com.optimaize.command4j.Command;
import com.optimaize.command4j.CommandExecutor;
import com.optimaize.command4j.Mode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * Base class for {@link RestWebService} and
 * {@link SoapWebService} implementations.
 */
public abstract class BaseWebService {

    private static final Logger logger = LoggerFactory.getLogger(BaseWebService.class);

    //Not using @Inject because then the stupid hk2 DI framework wants to do stuff.
    //not using @Autowired because variable name differs from type.
    //@Resource does the trick, only Spring handles it.
//    @Inject
    @Resource
    protected CommandExecutor executor;
    //same as above.
//    @Inject
    @Resource
    protected RequestRunningModeFactory modeFactory;


    /**
     * Removes the "throws Exception" from the api.
     */
    @NotNull
    protected <A, R> Optional<R> soapExceptionBarrier(
            Command<A, R> command,
            Mode mode,
            @Nullable A param
    ) throws SoapWebServiceException {
        try {
            return executor.execute(command, mode, param);
        } catch (Exception | AssertionError e) {
            if (e instanceof ServiceException) {
                //convert
                throw new SoapWebServiceException(((ServiceException) e).getFaultInfo());

                //keep these
            } else if (e instanceof SoapWebServiceException) {
                throw (SoapWebServiceException)e;

            } else {
                //should never arrive here. nothing we can do.
                String msg = "Unexpected late exception translation for: "+e.getMessage();
                logger.error(msg);
                throw new SoapWebServiceException(
                        FaultInfoBuilders.Server.internalServerError()
                        .applicationErrorCode(""+ErrorCodes.Server.INTERNAL_SERVER_ERROR.getCode())
                        .message(msg)
                        .retrySameLocation(null)
                        .retryOtherLocations(null)
                        .incidentId(null),
                        e
                );
            }
        }
    }

    /**
     * Removes the "throws Exception" from the api.
     */
    @NotNull
    protected <A, R> Optional<R> restExceptionBarrier(
            Command<A, R> command,
            Mode mode,
            @Nullable A param
    ) {
        try {
            return executor.execute(command, mode, param);
        } catch (Exception | AssertionError e) {
            if (e instanceof ServiceException) {
                throw (ServiceException)e;
//            if (e instanceof WebApplicationException) {
//                throw (WebApplicationException)e;
            } else {
                //should never arrive here. nothing we can do.
                String msg = "Unexpected late exception translation for: "+e.getMessage();
                logger.error(msg);
                throw InternalServerErrorServiceExceptions.internalServerError(msg);
            }
        }
    }

    protected Object possiblyWrapInEnvelope(boolean envelope, Object result) {
        if (envelope) {
            return FaultInfoEnvelope.success(result);
        }
        return result;
    }

}
