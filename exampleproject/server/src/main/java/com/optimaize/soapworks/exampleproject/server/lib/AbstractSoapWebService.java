package com.optimaize.soapworks.exampleproject.server.lib;

import com.google.common.base.Optional;
import com.optimaize.command4j.Command;
import com.optimaize.command4j.CommandExecutor;
import com.optimaize.command4j.Mode;
import com.optimaize.soapworks.server.SoapWebService;
import com.optimaize.soapworks.server.exception.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

/**
 */
public abstract class AbstractSoapWebService implements SoapWebService {

    private static final Logger logger = LoggerFactory.getLogger(AbstractSoapWebService.class);

    @Inject
    protected CommandExecutor executor;

    @Inject
    protected RequestRunningModeFactory modeFactory;


    /**
     * Removes the "throws Exception" from the api.
     */
    @NotNull
    protected <A, R> Optional<R> exceptionBarrier(
            Command<A, R> command,
            Mode mode,
            @Nullable A param
    ) throws AccessDeniedWebServiceException, InvalidInputWebServiceException, InternalServerErrorWebServiceException {
        try {
            return executor.execute(command, mode, param);
        } catch (Exception e) {
            if (e instanceof AccessDeniedWebServiceException) {
                throw (AccessDeniedWebServiceException)e;
            } else if (e instanceof InvalidInputWebServiceException) {
                throw (InvalidInputWebServiceException)e;
            } else if (e instanceof InternalServerErrorWebServiceException) {
                throw (InternalServerErrorWebServiceException)e;
            } else {
                //should never arrive here. nothing we can do.
                logger.error("Murphy: unexpected late exception translation!");
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

}
