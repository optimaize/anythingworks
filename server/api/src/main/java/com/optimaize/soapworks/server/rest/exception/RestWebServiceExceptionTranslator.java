package com.optimaize.soapworks.server.rest.exception;

import com.optimaize.command4j.ext.extensions.exception.exceptiontranslation.ExceptionTranslator;
import com.optimaize.soapworks.server.commandextensions.ExceptionMessageMaker;
import com.optimaize.soapworks.server.soap.exception.FaultBean;
import org.jetbrains.annotations.NotNull;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.ServiceUnavailableException;
import javax.ws.rs.WebApplicationException;

/**
 * Translates exceptions so that only BaseWebServiceException and subclasses make it to the client.
 *
 * @author Fabian Kessler
 */
public class RestWebServiceExceptionTranslator implements ExceptionTranslator {

    @NotNull
    private final ExceptionMessageMaker exceptionMessageMaker;
    private final boolean problemReported;

    /**
     * Sets the {@code problemReported} in the {@link FaultBean}.
     * <p>If {@code true} then it expects the user of this translator to log the exceptions.</p>
     */
    public RestWebServiceExceptionTranslator(@NotNull ExceptionMessageMaker exceptionMessageMaker, boolean problemReported) {
        this.exceptionMessageMaker = exceptionMessageMaker;
        this.problemReported = problemReported;
    }

    public boolean canTranslate(@NotNull Throwable t) {
        if (t instanceof WebApplicationException) {
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
            throw new InternalServerErrorException(message, t);

        } else if (t instanceof OutOfMemoryError) {
            throw new ServiceUnavailableException(message, 120L);

        } else {//includes RuntimeException, Exception, Error, Throwable:
            throw new InternalServerErrorException(message, t);
        }
    }

}
