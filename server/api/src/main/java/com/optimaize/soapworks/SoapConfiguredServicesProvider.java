package com.optimaize.soapworks;

import org.jetbrains.annotations.NotNull;

/**
 * Adds the path, that's all for now.
 */
public interface SoapConfiguredServicesProvider extends SoapServicesProvider {

    /**
     * The serives will be published under this absolute base path (starting with a slash)
     * plus {@link SoapWebService#getServicePath()} appended.
     */
    @NotNull
    String getPathPrefix();

}
