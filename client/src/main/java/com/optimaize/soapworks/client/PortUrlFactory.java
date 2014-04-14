package com.optimaize.soapworks.client;

import java.net.URL;

import com.optimaize.soapworks.client.Vocabulary;
import com.optimaize.soapworks.common.host.Host;
import org.jetbrains.annotations.NotNull;

/**
 * Creates the URL to be used in a {@link Vocabulary#Port port}.
 *
 * @author Eike Kettner
 * @since 28.05.12 13:15
 */
public interface PortUrlFactory {

    /**
     * @param servicePath see {@link Vocabulary#servicePath}
     */
    @NotNull
    URL createUrl(@NotNull Host host, @NotNull String servicePath);

}
