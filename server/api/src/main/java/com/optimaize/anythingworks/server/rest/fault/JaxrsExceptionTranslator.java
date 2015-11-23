package com.optimaize.anythingworks.server.rest.fault;

import com.optimaize.command4j.ext.extensions.exception.exceptiontranslation.ExceptionTranslator;
import com.optimaize.anythingworks.server.commandextensions.ExceptionMessageMaker;
import com.optimaize.anythingworks.server.common.exception.FaultBean;
import org.jetbrains.annotations.NotNull;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.ServiceUnavailableException;
import javax.ws.rs.WebApplicationException;

/**
 * Translates exceptions so that only {@link WebApplicationException} from JAX-RS and subclasses make it
 * to the client.
 *
 * <p>Use this if you use the JAX-RS exception classes.</p>
 *
 * @author Fabian Kessler
 */
@Deprecated
public class JaxrsExceptionTranslator implements ExceptionTranslator {

    @NotNull
    private final ExceptionMessageMaker exceptionMessageMaker;

    /**
     */
    public JaxrsExceptionTranslator(@NotNull ExceptionMessageMaker exceptionMessageMaker) {
        this.exceptionMessageMaker = exceptionMessageMaker;
    }

    public boolean canTranslate(@NotNull Throwable t) {
        if (t instanceof WebApplicationException) {
            return false; //leave as is
        }
        return true;
    }


    @NotNull @Override
    public Exception translate(@NotNull Throwable t) throws WebApplicationException {

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
