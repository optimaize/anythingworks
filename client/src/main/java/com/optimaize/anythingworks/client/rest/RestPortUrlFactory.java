package com.optimaize.anythingworks.client.rest;

import com.optimaize.anythingworks.client.Vocabulary;
import com.optimaize.anythingworks.common.host.Host;
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
