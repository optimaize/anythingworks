package com.optimaize.soapworks.exampleproject.server.lib;

import com.optimaize.command4j.ext.extensions.exception.exceptiontranslation.ExceptionTranslator;
import com.optimaize.soapworks.server.commandextensions.FixedStringExceptionMessageMaker;
import com.optimaize.soapworks.server.commandextensions.WebServiceExceptionTranslator;
import org.jetbrains.annotations.NotNull;

/**
 * ...
 *
 * @author Fabian Kessler
 */
public class DefaultServerExceptionTranslator implements ExceptionTranslator {

    private static final WebServiceExceptionTranslator wrapped = new WebServiceExceptionTranslator(
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
