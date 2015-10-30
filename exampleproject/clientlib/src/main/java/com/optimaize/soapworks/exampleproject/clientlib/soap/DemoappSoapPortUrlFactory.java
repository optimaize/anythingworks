package com.optimaize.soapworks.exampleproject.clientlib.soap;

import com.optimaize.soapworks.client.soap.AbstractSoapPortUrlFactory;
import com.optimaize.soapworks.common.host.Host;
import org.jetbrains.annotations.NotNull;

import java.net.URL;

/**
 */
public class DemoappSoapPortUrlFactory extends AbstractSoapPortUrlFactory {

    /**
     * This is updated whenever the server api version changes.
     */
    private static final String soapPrefix = "/soap/v1";

    @NotNull @Override
    public URL createUrl(@NotNull Host host, @NotNull String servicePath) {
        return createUrl(host, soapPrefix, servicePath);
    }
}
