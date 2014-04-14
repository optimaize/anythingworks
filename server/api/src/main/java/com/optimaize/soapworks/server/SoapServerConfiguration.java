package com.optimaize.soapworks.server;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Configuration for the soap services part of the web services.
 *
 * <p>If {@link #doUse()} says no then the getters are supposed to throw
 * {@link IllegalStateException}.</p>
 *
 * @author Fabian Kessler
 */
public interface SoapServerConfiguration {

    /**
     * Tells if the soap web server should be used at all.
     */
    boolean doUse();

    /**
     * @return The host name to use such as "example.com"
     * @throws IllegalStateException if not {@link #doUse()}
     */
    @NotNull
    String getHost() throws IllegalStateException;

    /**
     * @return The port number to use such as port 80.
     * @throws IllegalStateException if not {@link #doUse()}
     */
    int getPort() throws IllegalStateException;

    /**
     * @return <code>null</code> to let the server configure it himself based on the
     *         number of available cores.
     * @throws IllegalStateException if not {@link #doUse()}
     */
    @Nullable
    Integer getMaxThreadPoolSize() throws IllegalStateException;

    /**
     * @return 0 to not limit at all.
     * @throws IllegalStateException if not {@link #doUse()}
     */
    int getThreadPoolQueueLimit() throws IllegalStateException;

}
