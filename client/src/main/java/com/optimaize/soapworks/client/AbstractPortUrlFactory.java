package com.optimaize.soapworks.client;

import com.google.common.base.Throwables;
import com.optimaize.soapworks.common.host.Host;
import org.jetbrains.annotations.NotNull;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Fabian Kessler
 */
public abstract class AbstractPortUrlFactory implements PortUrlFactory {

    /**
     * @param pathPrefix anything you would like to have in between,
     *                   such as for example "/soap/v1".
     */
    @NotNull
    public URL createUrl(@NotNull Host host, @NotNull String pathPrefix, @NotNull String servicePath) {
        try {
            return new URL(host.toUriString() + pathPrefix + servicePath + "?wsdl");
        } catch (MalformedURLException e) {
            throw Throwables.propagate(e);
        }
    }
}
