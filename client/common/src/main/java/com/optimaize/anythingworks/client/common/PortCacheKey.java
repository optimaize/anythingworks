package com.optimaize.anythingworks.client.common;

import com.optimaize.command4j.cache.ExecutorCacheKey;
import com.optimaize.anythingworks.common.host.Host;
import org.jetbrains.annotations.NotNull;

/**
 * The key used for the cache object to store ports.
 * A port is always for one host.
 *
 * @author Fabian Kessler
 */
public class PortCacheKey<V> extends ExecutorCacheKey<V> {

    @NotNull
    private final Host host;

    @NotNull
    public static <V> PortCacheKey<V> create(@NotNull Class<V> type, @NotNull Host host) {
        //noinspection unchecked
        return new PortCacheKey(type, host);
    }
    private PortCacheKey(@NotNull Class<V> type, @NotNull Host host) {
        super(type);
        this.host = host;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PortCacheKey that = (PortCacheKey) o;

        if (!host.equals(that.host)) return false;
        if (!type.equals(that.type)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + host.hashCode();
        return result;
    }
}
