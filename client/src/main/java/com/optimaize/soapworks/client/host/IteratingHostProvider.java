package com.optimaize.soapworks.client.host;

import com.optimaize.soapworks.common.host.Host;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Impl that returns another host on each call to {@link #getHost()} by iterating the list of hosts.
 * If the list only contains one, then all calls return that.
 *
 * @author fab
 */
public class IteratingHostProvider implements HostProvider {
    
    @NotNull
    private final Set<Host> hosts = new LinkedHashSet<>();
    private int lastReturnedHost = -1;
    
    public IteratingHostProvider(@NotNull Host... hosts) {
        for (Host host : hosts) {
            if (host==null) throw new IllegalArgumentException("Host may not be null!");
            this.hosts.add(host);
        }
    }
    public IteratingHostProvider(@NotNull Collection<Host> hosts) {
        for (Host host : hosts) {
            if (host==null) throw new IllegalArgumentException("Host may not be null!");
            this.hosts.add(host);
        }
    }

    /**
     * This impl returns the next host in the list on each call by iterating the list of hosts.
     * If the list only contains one, then all calls return that.
     */
    @Nullable @Override
    public synchronized Host getHost() {
        int pos = getNextHostPos();
        if (pos==-1) {
            return returnNone();
        }
        Host ret = getHostAtPos(pos);
        lastReturnedHost = pos;
        return ret;
    }

    @NotNull
    private Host getHostAtPos(int pos) {
        int i=0;
        for (Host host : hosts) {
            if (i==pos) {
                return host;
            }
            i++;
        }
        throw new AssertionError("Dead code reached, this looks like a concurrency problem!");
    }

    @Nullable
    private Host returnNone() {
        lastReturnedHost = -1;
        return null;
    }

    private int getNextHostPos() {
        if (hosts.size()==0) return -1;
        int ret = lastReturnedHost+1;
        if (ret >= hosts.size()) {
            ret = 0;
        }
        return ret;
    }

    /**
     * This behaves like {@link #getHost} in this class, and forwards to the next one
     * in case the one that would be returned is the same as <code>exceptHost</code>.
     * @param exceptHost The host that is not acceptable, for example because it was just used and failed.
     */
    @Override @Nullable
    public synchronized Host getNextHost(@NotNull Host exceptHost) {
        int pos = getNextHostPos();
        if (pos==-1) {
            //don't reset lastReturnedHost to -1 here!
            return null;
        }
        Host ret = getHostAtPos(pos);
        if (exceptHost.equals(ret)) {
            pos++;
            if (pos >= hosts.size()) {
                pos = 0;
            }
            ret = getHostAtPos(pos);
            if (exceptHost.equals(ret)) {
                //don't reset lastReturnedHost to -1 here!
                return null;
            }
        }
        lastReturnedHost = pos;
        return ret;
    }

    @Override
    public Host getNextHost(@NotNull Collection<Host> exceptHosts) {
        //TODO this is a simplification (but probably not a bad one...)
        for (Host host : hosts) {
            if (exceptHosts.contains(host)) continue;
            return host;
        }
        return null;
    }

    @Override
    public synchronized boolean hasHost(@NotNull Host host) {
        return hosts.contains(host);
    }

    @Override
    public synchronized boolean addHost(@NotNull Host host) throws UnsupportedOperationException {
        return hosts.add(host);
    }

    @Override
    public synchronized boolean removeHost(@NotNull Host host) throws UnsupportedOperationException {
        return hosts.remove(host);
    }

    @Override
    public synchronized int size() {
        return hosts.size();
    }

}
