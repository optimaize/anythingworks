package com.optimaize.soapworks.common.exception;

/**
 * Tells who's likely to blame when exceptions occur.
 * <p>Based on this, decisions like auto-retry and logging can be made.</p>
 */
public enum Blame {

    /**
     * The problem is caused by the client.
     * Cases include:
     *  - authentication: not authenticated, account expired, request limit exceeded, ...
     *  - malformed request: invalid input params, unknown service name
     */
    CLIENT,

    /**
     * The problem is caused by the server.
     * Cases include:
     *  - server overload
     *  - offline for maintenance
     *  - internal server error while processing the request (bugs)
     */
    SERVER,

    /**
     * The client couldn't reach the server.
     */
    NETWORK,

    /**
     * At the point where a problem was detected it was not possible to tell who's to blame.
     */
    UNKNOWN,
    ;

    public static void assertSize(int expectedItems) {
        assert values().length == expectedItems : "Update the code calling this with " + expectedItems + "!";
    }

}

