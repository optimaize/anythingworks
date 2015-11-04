package com.optimaize.anythingworks.client.rest;

import com.optimaize.anythingworks.client.common.Keys;
import com.optimaize.anythingworks.client.common.PortCacheKey;
import com.optimaize.anythingworks.client.rest.http.RestHttpClient;
import com.optimaize.anythingworks.client.rest.http.RestHttpClientImpl;
import com.optimaize.command4j.ExecutionContext;
import com.optimaize.command4j.commands.BaseCommand;
import com.optimaize.anythingworks.common.host.Host;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.concurrent.Callable;

/**
 * Adds REST specific functionality to the BaseCommand.
 * @param <T> The RestServicePort type.
 */
public abstract class RestBaseCommand<T, A, R> extends BaseCommand<A, R> {

    @NotNull
    private final Class<T> portType;

    protected RestBaseCommand(@NotNull Class<T> portType) {
        this.portType = portType;
    }

    @NotNull
    protected Host getHost(@NotNull ExecutionContext ec) {
        return ec.getMode().get(Keys.HOST).get();
    }

    @NotNull
    protected RestPortUrlFactory getPortUrlFactory(@NotNull ExecutionContext ec) {
        return ec.getMode().get(RestKeys.REST_PORT_URL_FACTORY).get();
    }

    @NotNull
    protected URL makeBaseUrl(@NotNull ExecutionContext ec) {
        Host host = getHost(ec);
        RestPortUrlFactory urlFactory = getPortUrlFactory(ec);
        return urlFactory.createUrl(host);
    }

    @NotNull
    protected RestHttpClient makeClient(@NotNull final ExecutionContext ec) {
        URL baseUrl = makeBaseUrl(ec);
        return new RestHttpClientImpl.Builder()
                .basePath(baseUrl.toExternalForm())
                .userAgent("Java-Client")
                .build();
    }

    @NotNull
    protected PortCacheKey<T> createCacheKey(@NotNull Host host) {
        return PortCacheKey.create(portType, host);
    }

    @NotNull
    protected T getPort(@NotNull ExecutionContext ec) throws Exception {
        Host host = getHost(ec);
        return ec.getCache().get(createCacheKey(host), createPort(ec));
    }

    @NotNull
    protected abstract Callable<T> createPort(@NotNull final ExecutionContext ec);

}