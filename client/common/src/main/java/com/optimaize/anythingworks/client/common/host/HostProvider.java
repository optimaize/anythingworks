package com.optimaize.anythingworks.client.common.host;

import com.optimaize.anythingworks.common.host.Host;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

/**
 * Strategy for selecting a remote host to send the requests to.
 *
 * <p>Implementations really should be thread safe as it's very likely that they are used in multi-threaded
 * environments.</p>
 *
 * @author fab
 */
public interface HostProvider {

    /**
     * Returns the best host according to the strategy.
     *
     * <p>Basically all implementations should provide at least one host, but there are exceptions and
     * situations in which none is here or usable, hence <code>null</code> is possible.</p>
     */
    @Nullable
    Host getHost();

    /**
     * Returns the best host other than <code>exceptHost</code>.
     * @param exceptHost The host that is not acceptable, for example because it was just used and failed.
     * @return <code>null</code> in case no other host is available.
     */
    @Nullable
    Host getNextHost(@NotNull Host exceptHost);
    @Nullable
    Host getNextHost(@NotNull Collection<Host> exceptHosts);

    /**
     * Tells if the <code>host</code> is in the list of this provider.
     */
    boolean hasHost(@NotNull Host host);

    /**
     * Adds the specified host from the provider if it is not there yet.
     * @param host
     * @return <code>true</code> if it was not there, <code>false</code> if it was.
     * @throws UnsupportedOperationException This method is optional.
     */
    boolean addHost(@NotNull Host host) throws UnsupportedOperationException;

    /**
     * Removes the specified host from the provider if it is there.
     * @param host
     * @return <code>true</code> if it was there, <code>false</code> if not.
     * @throws UnsupportedOperationException This method is optional.
     */
    boolean removeHost(@NotNull Host host) throws UnsupportedOperationException;

    /**
     * @return tells how many hosts the provider contains.
     */
    int size();
}
