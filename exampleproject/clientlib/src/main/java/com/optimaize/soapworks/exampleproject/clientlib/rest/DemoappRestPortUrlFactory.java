package com.optimaize.soapworks.exampleproject.clientlib.rest;

import com.optimaize.soapworks.client.rest.AbstractRestPortUrlFactory;
import com.optimaize.soapworks.common.host.Host;
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
