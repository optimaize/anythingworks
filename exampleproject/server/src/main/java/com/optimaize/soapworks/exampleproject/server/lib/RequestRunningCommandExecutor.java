package com.optimaize.soapworks.exampleproject.server.lib;

import com.optimaize.command4j.CommandExecutor;
import com.optimaize.command4j.CommandExecutorBuilder;
import com.optimaize.command4j.ext.extensions.exception.exceptiontranslation.ExceptionTranslationExtension;
import com.optimaize.command4j.ext.extensions.logging.customlogging.CustomLoggingExtension;
import com.optimaize.command4j.ext.extensions.timeout.configurabletimeout.TimeoutExtension;
import com.optimaize.command4j.impl.CommandExecutorWrapper;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * ...
 *
 * @author Fabian Kessler
 */
@Service
public class RequestRunningCommandExecutor extends CommandExecutorWrapper {

    private static final Logger logger = LoggerFactory.getLogger(RequestRunningCommandExecutor.class);

    private CommandExecutor executor;

    @PostConstruct
    protected void init() {
        executor = create();
    }
    private CommandExecutor create() {
        return new CommandExecutorBuilder()
            .withExtension(new TimeoutExtension())
            .withExtension(new ExceptionTranslationExtension())
            .withExtension(new CustomLoggingExtension())
        .build();
    }


    @NotNull @Override
    protected CommandExecutor wrapped() {
        return executor;
    }



}
