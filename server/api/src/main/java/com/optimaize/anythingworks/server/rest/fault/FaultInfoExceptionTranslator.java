package com.optimaize.anythingworks.server.rest.fault;

import com.optimaize.anythingworks.common.fault.faultinfo.FaultInfo;
import com.optimaize.anythingworks.server.commandextensions.ExceptionMessageMaker;
import com.optimaize.anythingworks.server.rest.fault.exception.InternalServerErrorServiceExceptions;
import com.optimaize.anythingworks.server.rest.fault.exception.ServiceTemporarilyUnavailableServiceExceptions;
import com.optimaize.command4j.ext.extensions.exception.exceptiontranslation.ExceptionTranslator;
import org.jetbrains.annotations.NotNull;

/**
 * Translates exceptions so that only {@link com.optimaize.anythingworks.common.fault.exceptions.ServiceException}
 * from anythingworks and subclasses make it to the client. These contain a {@link FaultInfo}.
 *
 * <p>Use this if you use the exception classes provided by anythingworks.</p>
 *
 * @author Fabian Kessler
 */
public class FaultInfoExceptionTranslator implements ExceptionTranslator {

    @NotNull
    private final ExceptionMessageMaker exceptionMessageMaker;

    /**
     */
    public FaultInfoExceptionTranslator(@NotNull ExceptionMessageMaker exceptionMessageMaker) {
        this.exceptionMessageMaker = exceptionMessageMaker;
    }

    public boolean canTranslate(@NotNull Throwable t) {
        if (t instanceof com.optimaize.anythingworks.common.fault.exceptions.ServiceException) {
            return false; //leave as is
        }
        return true;
    }


    @NotNull @Override
    public Exception translate(@NotNull Throwable t) throws com.optimaize.anythingworks.common.fault.exceptions.ServiceException {

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

            return throwInternal(message);
        } else if (t instanceof OutOfMemoryError) {
            //disputable whether this should be done. server should be shut down... so that it can restart. the faster the better.
            throw ServiceTemporarilyUnavailableServiceExceptions.serviceTemporarilyUnavailable();
        } else {//includes RuntimeException, Exception, Error, Throwable:
            return throwInternal(message);
        }
    }

    private Exception throwInternal(String message) {
        throw InternalServerErrorServiceExceptions.internalServerError(message);
    }

}
