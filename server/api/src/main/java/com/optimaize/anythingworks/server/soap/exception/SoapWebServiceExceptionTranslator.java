package com.optimaize.anythingworks.server.soap.exception;

import com.optimaize.anythingworks.common.fault.exceptions.InternalServerErrorServiceException;
import com.optimaize.anythingworks.common.fault.exceptions.ServiceException;
import com.optimaize.anythingworks.common.fault.exceptions.ServiceTemporarilyUnavailableServiceException;
import com.optimaize.anythingworks.server.commandextensions.ExceptionMessageMaker;
import com.optimaize.anythingworks.server.common.fault.FaultInfos;
import com.optimaize.command4j.ext.extensions.exception.exceptiontranslation.ExceptionTranslator;
import org.jetbrains.annotations.NotNull;

/**
 * Translates exceptions so that only {@link SoapWebServiceException} makes it to the client.
 *
 * @author Fabian Kessler
 */
public class SoapWebServiceExceptionTranslator implements ExceptionTranslator {

    @NotNull
    private final ExceptionMessageMaker exceptionMessageMaker;

    /**
     */
    public SoapWebServiceExceptionTranslator(@NotNull ExceptionMessageMaker exceptionMessageMaker) {
        this.exceptionMessageMaker = exceptionMessageMaker;
    }

    public boolean canTranslate(@NotNull Throwable t) {
        if (t instanceof ServiceException) {
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
            throw new InternalServerErrorServiceException(
                    FaultInfos.Server.InternalServerError.internalServerError(message)
            );

        } else if (t instanceof OutOfMemoryError) {
            //disputable whether this should be done. server should be shut down... so that it can restart. the faster the better.
            throw new ServiceTemporarilyUnavailableServiceException(
                    FaultInfos.Server.ServiceTemporarilyUnavailable.serviceTemporarilyUnavailable()
            );
        } else {//includes RuntimeException, Exception, Error, Throwable:
            throw new InternalServerErrorServiceException(
                FaultInfos.Server.InternalServerError.internalServerError(message)
            );
        }
    }

}
