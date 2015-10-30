package com.optimaize.soapworks.client.rest;

import com.optimaize.soapworks.common.host.Host;
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
