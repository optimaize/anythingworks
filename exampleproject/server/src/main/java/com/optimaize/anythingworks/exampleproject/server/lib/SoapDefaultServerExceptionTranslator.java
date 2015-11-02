package com.optimaize.anythingworks.exampleproject.server.lib;

import com.optimaize.command4j.ext.extensions.exception.exceptiontranslation.ExceptionTranslator;
import com.optimaize.anythingworks.server.commandextensions.FixedStringExceptionMessageMaker;
import com.optimaize.anythingworks.server.soap.exception.SoapWebServiceExceptionTranslator;
import org.jetbrains.annotations.NotNull;

/**
 * ...
 *
 * @author Fabian Kessler
 */
public class SoapDefaultServerExceptionTranslator implements ExceptionTranslator {

    private static final SoapWebServiceExceptionTranslator wrapped = new SoapWebServiceExceptionTranslator(
            new FixedStringExceptionMessageMaker("Internal server error!"),
            true //someone has got to log the exceptions
    );

    public boolean canTranslate(@NotNull Throwable t) {
        return wrapped.canTranslate(t);
    }


    @NotNull @Override
    public Exception translate(@NotNull Throwable t) throws Exception {
        return wrapped.translate(t);
    }

}
