package com.optimaize.soapworks.client.soap;

import java.net.URL;

import com.optimaize.soapworks.client.Vocabulary;
import com.optimaize.soapworks.common.host.Host;
import org.jetbrains.annotations.NotNull;

/**
 * Creates the URL to be used in a {@link Vocabulary#Port port}.
 *
 * @author Eike Kettner
 */
public interface SoapPortUrlFactory {

    /**
     * @param servicePath see {@link Vocabulary#servicePath}
     */
    @NotNull
    URL createUrl(@NotNull Host host, @NotNull String servicePath);

}
