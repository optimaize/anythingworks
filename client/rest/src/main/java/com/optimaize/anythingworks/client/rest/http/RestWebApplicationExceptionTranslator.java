package com.optimaize.anythingworks.client.rest.http;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 *
 */
public interface RestWebApplicationExceptionTranslator {

    @NotNull
    WebApplicationException translate(@NotNull Response.Status status, @Nullable String message);

}
