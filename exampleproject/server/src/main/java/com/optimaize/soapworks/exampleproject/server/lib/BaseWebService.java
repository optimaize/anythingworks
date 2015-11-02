package com.optimaize.soapworks.exampleproject.server.lib;

import com.google.common.base.Optional;
import com.optimaize.command4j.Command;
import com.optimaize.command4j.CommandExecutor;
import com.optimaize.command4j.Mode;
import com.optimaize.soapworks.server.rest.Envelope;
import com.optimaize.soapworks.server.rest.RestWebService;
import com.optimaize.soapworks.server.soap.SoapWebService;
import com.optimaize.soapworks.server.soap.exception.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.WebApplicationException;

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
    ) throws AccessDeniedWebServiceException, InvalidInputWebServiceException, InternalServerErrorWebServiceException {
        try {
            return executor.execute(command, mode, param);
        } catch (Exception | AssertionError e) {
            if (e instanceof AccessDeniedWebServiceException) {
                throw (AccessDeniedWebServiceException)e;
            } else if (e instanceof InvalidInputWebServiceException) {
                throw (InvalidInputWebServiceException)e;
            } else if (e instanceof InternalServerErrorWebServiceException) {
                throw (InternalServerErrorWebServiceException)e;
            } else {
                //should never arrive here. nothing we can do.
                logger.error("Unexpected late exception translation!");
                throw new InternalServerErrorWebServiceException(
                        FaultBeanBuilders.Server.internalServerError()
                        .errorCode(1100)
                        .message("Unexpected late exception translation")
                        .retrySameServer(Retry.unknown())
                        .retryOtherServers(Retry.unknown())
                        .problemReported(true),
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
            if (e instanceof WebApplicationException) {
                throw (WebApplicationException)e;
            } else {
                //should never arrive here. nothing we can do.
                logger.error("Unexpected late exception translation!");
                throw new InternalServerErrorException(e);
            }
        }
    }

    protected Object possiblyWrapInEnvelope(boolean envelope, Object result) {
        if (envelope) {
            return Envelope.success(result);
        }
        return result;
    }

}
