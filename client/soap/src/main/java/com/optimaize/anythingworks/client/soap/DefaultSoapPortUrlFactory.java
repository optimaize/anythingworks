package com.optimaize.anythingworks.client.soap;

import com.optimaize.anythingworks.common.host.Host;
import org.jetbrains.annotations.NotNull;

import java.net.URL;

/**
 * This super simple default impl just concatenates the url base and
 * service path.
 *
 * @author Eike Kettner
 */
public class DefaultSoapPortUrlFactory extends AbstractSoapPortUrlFactory {

    @NotNull @Override
    public URL createUrl(@NotNull Host host, @NotNull String servicePath) {
        return createUrl(host, "", servicePath);
    }
}
