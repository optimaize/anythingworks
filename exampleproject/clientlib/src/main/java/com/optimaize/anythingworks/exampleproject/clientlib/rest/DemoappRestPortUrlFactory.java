package com.optimaize.anythingworks.exampleproject.clientlib.rest;

import com.optimaize.anythingworks.client.rest.AbstractRestPortUrlFactory;
import com.optimaize.anythingworks.common.host.Host;
import org.jetbrains.annotations.NotNull;

import java.net.URL;

/**
 */
public class DemoappRestPortUrlFactory extends AbstractRestPortUrlFactory {

    /**
     * This is updated whenever the server api version changes.
     */
    private static final String restPrefix = "/rest/v1";

    @NotNull @Override
    public URL createUrl(@NotNull Host host) {
        return createUrl(host, restPrefix);
    }
}
