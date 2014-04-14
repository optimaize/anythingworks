package com.optimaize.soapworks.exampleproject.server.lib;

import com.optimaize.command4j.ext.extensions.exception.exceptiontranslation.ExceptionTranslator;
import com.optimaize.soapworks.commandextensions.WebServiceExceptionTranslator;
import org.jetbrains.annotations.NotNull;

/**
 * ...
 *
 * @author Fabian Kessler
 */
public class DefaultServerExceptionTranslator implements ExceptionTranslator {

    private static final WebServiceExceptionTranslator wrapped = new WebServiceExceptionTranslator();

    public boolean canTranslate(@NotNull Throwable t) {
        return wrapped.canTranslate(t);
    }


    @NotNull @Override
    public Exception translate(@NotNull Throwable t) throws Exception {
        return wrapped.translate(t);
    }

//    private void logPerm(String apiKey, WsRequestRunnerTask task, Throwable t) {
//        permLog.error("Logging error for User["+apiKey+"] and Task["+ tryToGetToString(task) +"']!");
//        stackTraceLog.error(t.getMessage(), t);
//    }
//
//    private String tryToGetToString(@NotNull WsRequestRunnerTask task) {
//        try {
//            return task.toString();
//        } catch (Exception e) {
//            return "(Exception for task.toString())";
//        }
//    }

}
