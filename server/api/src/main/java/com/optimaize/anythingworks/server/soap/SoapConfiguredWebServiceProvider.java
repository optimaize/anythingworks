package com.optimaize.anythingworks.server.soap;

import org.jetbrains.annotations.NotNull;

/**
 * Adds the path, that's all for now.
 */
public interface SoapConfiguredWebServiceProvider extends SoapWebServiceProvider {

    /**
     * The services will be published under this absolute base path (starting with a slash, ending in a slash)
     * plus {@link SoapWebService#getServicePath()} appended.
     */
    @NotNull
    String getPathPrefix();

}
