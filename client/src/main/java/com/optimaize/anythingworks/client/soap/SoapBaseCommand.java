package com.optimaize.anythingworks.client.soap;

import com.optimaize.command4j.ExecutionContext;
import com.optimaize.command4j.commands.BaseCommand;
import com.optimaize.anythingworks.client.Keys;
import com.optimaize.anythingworks.client.PortCacheKey;
import com.optimaize.anythingworks.common.host.Host;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.concurrent.Callable;

/**
 * Adds SOAP (WSDL) specific functionality to the BaseCommand.
 * @param <T> The wsdl port type.
 */
public abstract class SoapBaseCommand<T, A, R> extends BaseCommand<A, R> {

    @NotNull
    private final Class<T> wsdlPortType;

    protected SoapBaseCommand(@NotNull Class<T> wsdlPortType) {
        this.wsdlPortType = wsdlPortType;
    }

    @NotNull
    protected Host getHost(@NotNull ExecutionContext ec) {
        return ec.getMode().get(Keys.HOST).get();
    }

    @NotNull
    protected SoapPortUrlFactory getPortUrlFactory(@NotNull ExecutionContext ec) {
        return ec.getMode().get(Keys.SOAP_PORT_URL_FACTORY).get();
    }

    @NotNull
    protected URL makeUrl(@NotNull ExecutionContext ec, @NotNull String servicePath) {
        Host host = getHost(ec);
        SoapPortUrlFactory urlFactory = getPortUrlFactory(ec);
        return urlFactory.createUrl(host, servicePath);
    }

    @NotNull
    protected PortCacheKey<T> createCacheKey(@NotNull Host host) {
        return PortCacheKey.create(wsdlPortType, host);
    }

    @NotNull
    protected T getPort(@NotNull ExecutionContext ec) throws Exception {
        Host host = getHost(ec);
        return ec.getCache().get(createCacheKey(host), createPort(ec));
    }

    @NotNull
    protected abstract Callable<T> createPort(@NotNull final ExecutionContext ec);

}