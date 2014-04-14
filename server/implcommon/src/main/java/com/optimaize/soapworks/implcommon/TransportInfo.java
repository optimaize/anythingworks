package com.optimaize.soapworks.implcommon;

import com.optimaize.soapworks.common.host.Host;
import org.jetbrains.annotations.NotNull;

/**
 * immutable
 */
public class TransportInfo {

    @NotNull
    private final Host host;
    @NotNull
    private final String basePath;

    /**
     * @param basePath Starts and ends with a slash, thus the minimal is to pass in "/".
     */
    public TransportInfo(@NotNull Host host, @NotNull String basePath) {
        this.host     = host;
        if (!basePath.startsWith("/") || !basePath.endsWith("/")) {
            throw new IllegalArgumentException("Base path must start and end with a slash!");
        }
        this.basePath = basePath;
    }

    /**
     * @return something like "http://api.example.com:80/foo/"
     */
    @NotNull
    public String toUriString() {
        return host.toUriString() + basePath;
    }

        @NotNull
    public Host getHost() {
        return host;
    }

    @NotNull
    public String getBasePath() {
        return basePath;
    }


    @Override
    public String toString() {
        return toUriString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransportInfo that = (TransportInfo) o;

        if (!basePath.equals(that.basePath)) return false;
        if (!host.equals(that.host)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = host.hashCode();
        result = 31 * result + basePath.hashCode();
        return result;
    }
}
