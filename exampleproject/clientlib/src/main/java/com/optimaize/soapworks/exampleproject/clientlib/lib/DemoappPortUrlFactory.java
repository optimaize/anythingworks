package com.optimaize.soapworks.exampleproject.clientlib.lib;

import com.optimaize.soapworks.client.AbstractPortUrlFactory;
import com.optimaize.soapworks.common.host.Host;
import org.jetbrains.annotations.NotNull;

import java.net.URL;

/**
 */
public class DemoappPortUrlFactory extends AbstractPortUrlFactory {

    /**
     * This is updated whenever the server api version changes.
     */
    private static final String soapPrefix = "/soap/v1";

    @NotNull @Override
    public URL createUrl(@NotNull Host host, @NotNull String servicePath) {
        return createUrl(host, soapPrefix, servicePath);
    }
}
