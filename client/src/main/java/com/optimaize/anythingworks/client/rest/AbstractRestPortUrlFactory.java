package com.optimaize.anythingworks.client.rest;

import com.google.common.base.Throwables;
import com.optimaize.anythingworks.common.host.Host;
import org.jetbrains.annotations.NotNull;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Fabian Kessler
 */
public abstract class AbstractRestPortUrlFactory implements RestPortUrlFactory {

    /**
     * @param pathPrefix anything you would like to have in between,
     *                   such as for example "/rest/v1".
     */
    @NotNull
    public URL createUrl(@NotNull Host host, @NotNull String pathPrefix) {
        try {
            return new URL(host.toUriString() + pathPrefix);
        } catch (MalformedURLException e) {
            throw Throwables.propagate(e);
        }
    }
}
