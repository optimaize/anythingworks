package com.optimaize.anythingworks.exampleproject.server.lib;

import com.optimaize.command4j.ext.extensions.exception.exceptiontranslation.ExceptionTranslator;
import com.optimaize.anythingworks.server.commandextensions.FixedStringExceptionMessageMaker;
import com.optimaize.anythingworks.server.rest.exception.RestWebServiceExceptionTranslator;
import org.jetbrains.annotations.NotNull;

/**
 * Works using the {@link RestWebServiceExceptionTranslator}.
 *
 * @author Fabian Kessler
 */
public class RestDefaultServerExceptionTranslator implements ExceptionTranslator {

    private static final ExceptionTranslator wrapped = new RestWebServiceExceptionTranslator(
            new FixedStringExceptionMessageMaker("Internal server error!")
    );

    public boolean canTranslate(@NotNull Throwable t) {
        return wrapped.canTranslate(t);
    }


    @NotNull @Override
    public Exception translate(@NotNull Throwable t) throws Exception {
        return wrapped.translate(t);
    }

}
