package com.optimaize.soapworks.client.exception;

/**
 * Used in {@link FaultInfo}, tells who's to blame for a {@link ServiceException service exception}.
 *
 * @author fab
 */
public enum Blame {

    /**
     * A problem on the server which is not one of the other SERVER_ kinds.
     */
    SERVER_UNKNOWN,
    /**
     * A problem on the server in the code.
     */
    SERVER_CODE,
    /**
     * A problem on the server in the data.
     */
    SERVER_DATA,
    /**
     * A problem on the server that may be just temporary.
     */
    SERVER_CIRCUMSTANCE,
    /**
     * The server is busy or takes too long to execute.
     */
    SERVER_PERFORMANCE,

    /**
     * The client sent invalid data.
     */
    CLIENT_DATA,
    /**
     * The client
     */
    CLIENT_LOGIC,
    /**
     * For example when the client sends more concurrent requests than permitted.
     */
    CLIENT_BEHAVIOR,
    /**
     * Programming errors on the client side.
     */
    CLIENT_CODE,

    /**
     * Such as network timeouts or unresolvable hosts.
     */
    NETWORK,

    /**
     * When it's not possible to say who's to blame.
     */
    UNKNOWN;

    boolean isClient() {
        if (this==CLIENT_DATA)     return true;
        if (this==CLIENT_LOGIC)    return true;
        if (this==CLIENT_BEHAVIOR) return true;
        if (this==CLIENT_CODE)     return true;
        return false;
    }

    boolean isServer() {
        if (this==SERVER_UNKNOWN)      return true;
        if (this==SERVER_CODE)         return true;
        if (this==SERVER_DATA)         return true;
        if (this==SERVER_CIRCUMSTANCE) return true;
        if (this==SERVER_PERFORMANCE)  return true;
        return false;
    }

    boolean isNetwork() {
        if (this==NETWORK)    return true;
        return false;
    }

}
