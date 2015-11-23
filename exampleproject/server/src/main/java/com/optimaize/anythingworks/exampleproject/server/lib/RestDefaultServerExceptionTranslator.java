package com.optimaize.anythingworks.exampleproject.server.lib;

import com.optimaize.anythingworks.server.commandextensions.CauseMessageExceptionMessageMaker;
import com.optimaize.anythingworks.server.rest.fault.FaultInfoExceptionTranslator;
import com.optimaize.command4j.ext.extensions.exception.exceptiontranslation.ExceptionTranslator;
import com.optimaize.anythingworks.server.rest.fault.JaxrsExceptionTranslator;
import org.jetbrains.annotations.NotNull;

/**
 * Works using the {@link FaultInfoExceptionTranslator} or {@link JaxrsExceptionTranslator}.
 *
 * @author Fabian Kessler
 */
public class RestDefaultServerExceptionTranslator implements ExceptionTranslator {

    private static final ExceptionTranslator wrapped = new FaultInfoExceptionTranslator(
            new CauseMessageExceptionMessageMaker()
//            new FixedStringExceptionMessageMaker("Internal server error!")
    );
//    private static final ExceptionTranslator wrapped = new JaxrsExceptionTranslator(
//            new CauseMessageExceptionMessageMaker()
////            new FixedStringExceptionMessageMaker("Internal server error!")
//    );

    public boolean canTranslate(@NotNull Throwable t) {
        return wrapped.canTranslate(t);
    }


    @NotNull @Override
    public Exception translate(@NotNull Throwable t) throws Exception {
        return wrapped.translate(t);
    }

}
