package com.optimaize.soapworks.client.rest;

import com.optimaize.soapworks.client.Vocabulary;
import com.optimaize.soapworks.common.host.Host;
import org.jetbrains.annotations.NotNull;

import java.net.URL;

/**
 * Creates the URL to be used in a {@link Vocabulary#Port port}.
 *
 * @author Eike Kettner
 */
public interface RestPortUrlFactory {

    /**
     */
    @NotNull
    URL createUrl(@NotNull Host host);

}
