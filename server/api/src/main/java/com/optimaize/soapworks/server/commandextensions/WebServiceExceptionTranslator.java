package com.optimaize.soapworks.server.commandextensions;

import com.optimaize.command4j.ext.extensions.exception.exceptiontranslation.ExceptionTranslator;
import com.optimaize.soapworks.server.exception.*;
import com.optimaize.soapworks.server.exception.InternalServerErrorWebServiceException;
import org.jetbrains.annotations.NotNull;

/**
 * Translates exceptions so that only BaseWebServiceException and subclasses make it to the client.
 *
 * @author Fabian Kessler
 */
public class WebServiceExceptionTranslator implements ExceptionTranslator {

    @NotNull
    private final ExceptionMessageMaker exceptionMessageMaker;
    private final boolean problemReported;

    /**
     * Sets the {@code problemReported} in the {@link com.optimaize.soapworks.server.exception.FaultBean}.
     * <p>If {@code true} then it expects the user of this translator to log the exceptions.</p>
     */
    public WebServiceExceptionTranslator(@NotNull ExceptionMessageMaker exceptionMessageMaker, boolean problemReported) {
        this.exceptionMessageMaker = exceptionMessageMaker;
        this.problemReported = problemReported;
    }

    public boolean canTranslate(@NotNull Throwable t) {
        if (t instanceof BaseWebServiceException) {
            return false; //leave as is
        }
        return true;
    }


    @NotNull @Override
    public Exception translate(@NotNull Throwable t) throws Exception {

        /*
         * Impl notes:
         *
         * If we get here then it means that an exception was thrown in internal code.
         *
         * The user's web service input data was accepted already. Therefore we can't blame him anymore.
         * Some of these exceptions are likely caused by the input parameters.
          *
         * Either the params should not have been accepted in the first place, or
         * the combination hit a scenario on the server for which it was not prepared.
         * Both cases need fixing.
         *
         *
         */

        String message = exceptionMessageMaker.make(t);

        if (t instanceof IllegalArgumentException
                || t instanceof NullPointerException
                || t instanceof IllegalStateException
                || t instanceof UnsupportedOperationException
                || t instanceof AssertionError) {
            throw new InternalServerErrorWebServiceException(
                    FaultBeanBuilders.Server.internalServerError()
                    .errorCode(ErrorCodes.Server.SERVER_ERROR.getCode())
                    .message(message)
                    .retrySameServer(Retry.no()) //we assume it's persistent, not temporary.
                    .retryOtherServers(Retry.no())
                    .problemReported(problemReported)
            );

        } else if (t instanceof OutOfMemoryError) {
            throw new InternalServerErrorWebServiceException(
                    FaultBeanBuilders.Server.internalServerError()
                            .errorCode(ErrorCodes.Server.SERVER_ERROR.getCode())
                            .message(message)
                            .retrySameServer(Retry.later(120L)) //give it some time to reboot...
                            .retryOtherServers(Retry.now()) //hopefully it's not the user input that causes the out of memory, and kills other places too.
                            .problemReported(problemReported)
            );

        } else {//includes RuntimeException, Exception, Error, Throwable:
            throw new InternalServerErrorWebServiceException(
                    FaultBeanBuilders.Server.internalServerError()
                            .errorCode(ErrorCodes.Server.SERVER_ERROR.getCode())
                            .message(message)
                            .retrySameServer(Retry.unknown()) //we can't be sure whether it was temporary or persistent.
                            .retryOtherServers(Retry.unknown())
                            .problemReported(problemReported)
            );

        }
    }

}
