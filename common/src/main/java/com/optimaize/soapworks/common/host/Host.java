package com.optimaize.soapworks.common.host;

import org.jetbrains.annotations.NotNull;

import java.util.Locale;

/**
 * immutable
 */
public final class Host {

    @NotNull
    private final String hostName;
    @NotNull
    private final Protocol protocol;
    private final int portNumber;


    /**
     * @param hostName for example "api.example.com"
     * @param protocol
     * @param portNumber for example port 80.
     */
    public Host(@NotNull String hostName, @NotNull Protocol protocol, int portNumber) {
        this.protocol   = protocol;
        this.hostName   = hostName;
        this.portNumber = portNumber;
    }
    public Host(@NotNull String hostName, @NotNull Protocol protocol) {
        this(hostName, protocol, protocol.getDefaultPortNumber());
    }
    public Host(@NotNull String hostName, int portNumber) {
        this(hostName, Protocol.HTTP, portNumber);
    }
    public Host(@NotNull String hostName) {
        this(hostName, Protocol.HTTP, Protocol.HTTP.getDefaultPortNumber());
    }


    /**
     * @return something like "http://api.example.com:80"
     */
    @NotNull
    public String toUriString() {
        return protocol.name().toLowerCase(Locale.ENGLISH) + "://" + hostName + ":" + portNumber;
    }


    @NotNull
    public Protocol getProtocol() {
        return protocol;
    }

    @NotNull
    public String getHostName() {
        return hostName;
    }

    public int getPortNumber() {
        return portNumber;
    }

    @Override
    public String toString() {
        return toUriString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Host host = (Host) o;

        if (portNumber != host.portNumber) return false;
        if (!hostName.equals(host.hostName)) return false;
        if (protocol != host.protocol) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = hostName.hashCode();
        result = 31 * result + protocol.hashCode();
        result = 31 * result + portNumber;
        return result;
    }

}
