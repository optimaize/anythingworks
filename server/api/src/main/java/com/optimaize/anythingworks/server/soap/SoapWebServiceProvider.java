package com.optimaize.anythingworks.server.soap;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Interface for grouped contributions of {@link SoapWebService} instances.
 */
public interface SoapWebServiceProvider {
    @NotNull
    List<SoapWebService> getAll();
}
