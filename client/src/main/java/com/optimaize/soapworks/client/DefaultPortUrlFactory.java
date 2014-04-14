package com.optimaize.soapworks.client;

import com.optimaize.soapworks.common.host.Host;
import org.jetbrains.annotations.NotNull;

import java.net.URL;

/**
 * This super simple default impl just concatenates the url base and
 * service path.
 *
 * @author Eike Kettner
 */
public class DefaultPortUrlFactory extends AbstractPortUrlFactory {

    @NotNull @Override
    public URL createUrl(@NotNull Host host, @NotNull String servicePath) {
        return createUrl(host, "", servicePath);
    }
}
