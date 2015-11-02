package com.optimaize.anythingworks.server.commandextensions;

import org.jetbrains.annotations.NotNull;

/**
 * @author Fabian Kessler
 */
public interface ExceptionMessageMaker {

    @NotNull
    String make(@NotNull Throwable t);

}
