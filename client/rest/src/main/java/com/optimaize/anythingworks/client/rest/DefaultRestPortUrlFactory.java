package com.optimaize.anythingworks.client.rest;

import com.optimaize.anythingworks.common.host.Host;
import org.jetbrains.annotations.NotNull;

import java.net.URL;

/**
 * This super simple default impl just concatenates the url base and
 * service path.
 *
 * @author Fabian Kessler
 */
public class DefaultRestPortUrlFactory extends AbstractRestPortUrlFactory {

    @NotNull @Override
    public URL createUrl(@NotNull Host host) {
        return createUrl(host, "");
    }
}
