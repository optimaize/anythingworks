package com.optimaize.soapworks.commandextensions;

import com.optimaize.command4j.ext.extensions.exception.exceptiontranslation.ExceptionTranslator;
import com.optimaize.soapworks.exception.*;
import com.optimaize.soapworks.exception.InternalServerErrorWebServiceException;
import org.jetbrains.annotations.NotNull;

/**
 * Translates exceptions so that only BaseWebServiceException (and subclasses) makes it to the client.
 *
 * @author Fabian Kessler
 */
public class WebServiceExceptionTranslator implements ExceptionTranslator {

    public boolean canTranslate(@NotNull Throwable t) {
        if (t instanceof BaseWebServiceException) {
            return false; //leave as is
        }
        return true;
    }


    @NotNull @Override
    public Exception translate(@NotNull Throwable t) throws Exception {
        if (t instanceof IllegalArgumentException) {
            throw new InternalServerErrorWebServiceException(t, Retry.no(), true);
        } else if (t instanceof IllegalStateException) {
            throw new InternalServerErrorWebServiceException(t, Retry.no(), true);
        } else if (t instanceof UnsupportedOperationException) {
            throw new InternalServerErrorWebServiceException(t, Retry.no(), true);
        } else if (t instanceof NullPointerException) {
            throw new InternalServerErrorWebServiceException(t, Retry.no(), true);
        } else if (t instanceof RuntimeException) {
            throw new InternalServerErrorWebServiceException(t, Retry.unknown(), true);
        } else if (t instanceof AssertionError) {
            throw new InternalServerErrorWebServiceException(t, Retry.no(), true);
        } else if (t instanceof OutOfMemoryError) {
            throw new InternalServerErrorWebServiceException(t, Retry.later(120L), true); //give it some time to reboot...
        } else if (t instanceof Error) {
            throw new InternalServerErrorWebServiceException(t, Retry.unknown(), true);
        } else {
            //noinspection ConstantConditions
            assert t instanceof Throwable;
            throw new InternalServerErrorWebServiceException(t, Retry.unknown(), true);
        }
    }

}
