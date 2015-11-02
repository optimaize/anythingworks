package com.optimaize.anythingworks.server.commandextensions;

import org.jetbrains.annotations.NotNull;

/**
 * Uses the same "fixed string" for all exception messages, for example "Internal server error".
 *
 * This is useful to keep the real and possibly confidential exception message and stack trace internal.
 * You certainly need to log this information so that technicians can analyze it later. Because the
 * end user won't have anything usefule to hand it.
 *
 * @author Fabian Kessler
 */
public class FixedStringExceptionMessageMaker implements ExceptionMessageMaker {

    @NotNull
    private final String string;

    public FixedStringExceptionMessageMaker(@NotNull String string) {
        this.string = string;
    }

    @NotNull
    @Override
    public String make(@NotNull Throwable t) {
        return string;
    }
}
