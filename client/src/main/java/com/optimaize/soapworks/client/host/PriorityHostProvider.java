package com.optimaize.soapworks.client.host;

import com.optimaize.soapworks.common.host.Host;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

/**
 * Impl that returns the hosts with the highest priority on each call to {@link #getHost()}.
 *
 * <p>If multiple hosts have the same priority then another is chosen on each call by iterating.</p>
 *
 * <p>This provider is well suited for failover and load balancing. The priority hosts are used as
 * long as they work fine, and as soon as they fail then the less important ones are used.
 * It is similar to the concept of mail servers with MX records priority (in opposite order).</p>
 *
 * @author fab
 */
public class PriorityHostProvider implements HostProvider {

    @NotNull
    private final Set<Integer> priorities = new TreeSet<>(new Comparator<Integer>() {
        @Override public int compare(Integer o1, Integer o2) {
            if (o1<o2) return 1;
            if (o1>o2) return -1;
            return 0;
        }
    });

    @NotNull
    private final Map<Integer, IteratingHostProvider> hosts = new HashMap<>();


    /**
     * This impl returns the next host in the list on each call by iterating the list of hosts.
     * If the list only contains one, then all calls return that.
     */
    @Nullable @Override
    public synchronized Host getHost() {
        for (Integer priority : priorities) {
            IteratingHostProvider iteratingHostProvider = hosts.get(priority);
            if (iteratingHostProvider!=null) { //this could only be null if we had a synchronization problem or a bug.
                Host host = iteratingHostProvider.getHost();
                if (host!=null) return host;
            }
        }
        return null;
    }

    /**
     * This behaves like {@link #getHost} in this class, and forwards to the next one
     * in case the one that would be returned is the same as <code>exceptHost</code>.
     * @param exceptHost The host that is not acceptable, for example because it was just used and failed.
     */
    @Override @Nullable
    public synchronized Host getNextHost(@NotNull Host exceptHost) {
        return getNextHost(Collections.singleton(exceptHost));
    }

    @Override
    public Host getNextHost(@NotNull Collection<Host> exceptHosts) {
        for (Integer priority : priorities) {
            IteratingHostProvider iteratingHostProvider = hosts.get(priority);
            if (iteratingHostProvider!=null) { //this could only be null if we had a synchronization problem or a bug.
                Host host = iteratingHostProvider.getNextHost(exceptHosts);
                if (host!=null) return host;
            }
        }
        return null;
    }

    @Override
    public synchronized boolean hasHost(@NotNull Host host) {
        return (getProviderContainingHost(host)!=null);
    }

    /**
     * Adds the host with a default priority of 50.
     */
    @Override
    public synchronized boolean addHost(@NotNull Host host) throws UnsupportedOperationException {
        return addHost(host, 50);
    }
    /**
     * Adds the host with the given priority.
     * <p>If the host is here already but with another priority then the prio will be changed, and true is returned.</p>
     * <p>Multiple hosts may have the same priority, and are used alternately.</p>
     * @param priority The higher the more important.
     */
    public synchronized boolean addHost(@NotNull Host host, int priority) throws UnsupportedOperationException {
        ProviderWithPrio provider = getProviderContainingHost(host);
        if (provider!=null) {
            if (provider.prio == priority) {
                return false; //already have it
            } else {
                removeHost(host);
            }
        }
        IteratingHostProvider iteratingHostProvider = hosts.get(priority);
        if (iteratingHostProvider==null) {
            iteratingHostProvider = new IteratingHostProvider();
            hosts.put(priority, iteratingHostProvider);
            priorities.add(priority);
        }
        iteratingHostProvider.addHost(host);
        return true;
    }

    @Override
    public synchronized boolean removeHost(@NotNull Host host) throws UnsupportedOperationException {
        ProviderWithPrio provider = getProviderContainingHost(host);
        if (provider==null) return false;
        provider.provider.removeHost(host);
        if (provider.provider.size()==0) {
            hosts.remove(provider.prio);
            priorities.remove(provider.prio);
        }
        return true;
    }

    @Override
    public synchronized int size() {
        int ret = 0;
        for (IteratingHostProvider iteratingHostProvider : hosts.values()) {
            ret += iteratingHostProvider.size();
        }
        return ret;
    }

    @Nullable
    private ProviderWithPrio getProviderContainingHost(@NotNull Host host) {
        for (Map.Entry<Integer, IteratingHostProvider> entry : hosts.entrySet()) {
            if (entry.getValue().hasHost(host)) {
                return new ProviderWithPrio(entry.getKey(), entry.getValue());
            }
        }
        return null;
    }

    private static class ProviderWithPrio {
        int prio;
        HostProvider provider;
        private ProviderWithPrio(int prio, HostProvider provider) {
            this.prio = prio;
            this.provider = provider;
        }
    }

}
