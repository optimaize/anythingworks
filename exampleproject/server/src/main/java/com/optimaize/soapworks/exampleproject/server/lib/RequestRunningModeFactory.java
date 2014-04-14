package com.optimaize.soapworks.exampleproject.server.lib;

import com.optimaize.command4j.Mode;
import com.optimaize.command4j.ext.extensions.exception.exceptiontranslation.ExceptionTranslationExtension;
import com.optimaize.command4j.ext.extensions.logging.customlogging.CommandExecutionLoggerFactoryImpl;
import com.optimaize.command4j.ext.extensions.logging.customlogging.CustomLoggingExtension;
import com.optimaize.command4j.ext.extensions.timeout.configurabletimeout.TimeoutExtension;
import com.optimaize.command4j.lang.Duration;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Provides Mode instances that contain the minimum.
 *
 * @author Fabian Kessler
 */
@Service
public class RequestRunningModeFactory {

//        protected static final Logger permLog = LoggerFactory.getLogger("onoserver.permanentproblem");
//        protected static final Logger stackTraceLog = LoggerFactory.getLogger("onoserver.stacktrace");
        protected static final Logger logger = LoggerFactory.getLogger(RequestRunningModeFactory.class);

    @NotNull
    public Mode defaultMode() {
        return Mode.create()
                .with(TimeoutExtension.TIMEOUT, Duration.millis(5000))
                .with(ExceptionTranslationExtension.TRANSLATOR, new DefaultServerExceptionTranslator())
                .with(CustomLoggingExtension.LOGGER, new CommandExecutionLoggerFactoryImpl(logger))
        ;
    }

}
