package com.optimaize.anythingworks.client.common.host;

import com.optimaize.anythingworks.common.host.Host;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

/**
 * This implementation knows just one host, and always returns that one for all requests.
 */
public class SingleHostProvider implements HostProvider {

    @NotNull
    private final Host host;

    public SingleHostProvider(@NotNull Host host) {
        this.host = host;
    }

    @Override @NotNull
    public Host getHost() {
        return host;
    }

    @Override @Nullable
    public Host getNextHost(@NotNull Host exceptHost) {
        if (host.equals(exceptHost)) return null;
        return host;
    }

    @Override
    public Host getNextHost(@NotNull Collection<Host> exceptHosts) {
        if (exceptHosts.contains(host)) return null;
        return host;
    }

    @Override
    public boolean hasHost(@NotNull Host host) {
        return this.host.equals(host);
    }

    @Override
    public boolean addHost(@NotNull Host host) throws UnsupportedOperationException {
        return notSupported();
    }
    @Override
    public boolean removeHost(@NotNull Host host) throws UnsupportedOperationException {
        return notSupported();
    }

    @Override
    public int size() {
        return 0;
    }

    private boolean notSupported() {
        throw new UnsupportedOperationException("This impl only supports one host!");
    }
}
