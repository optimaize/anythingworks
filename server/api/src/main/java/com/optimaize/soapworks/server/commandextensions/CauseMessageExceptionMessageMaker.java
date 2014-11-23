package com.optimaize.soapworks.server.commandextensions;

import org.jetbrains.annotations.NotNull;

/**
 * Uses the message of the cause.
 *
 * <p>Beware that this might reveal sensitive information. You may want to use the {@link FixedStringExceptionMessageMaker}
 * in production to untrusted users instead.</p>
 *
 * @author Fabian Kessler
 */
public class CauseMessageExceptionMessageMaker implements ExceptionMessageMaker {

    @NotNull
    @Override
    public String make(@NotNull Throwable t) {
        return t.getMessage();
    }
}
