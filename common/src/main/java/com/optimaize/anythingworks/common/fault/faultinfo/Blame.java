package com.optimaize.anythingworks.common.fault.faultinfo;

/**
 * Tells who's likely to blame when exceptions occur.
 *
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
     * The problem is caused by the server, or by the network in between.
     * Cases include:
     *  - server overload
     *  - offline for maintenance
     *  - internal server error while processing the request (bugs)
     *  - networking: timeout, dns lookup failed, offline
     *
     * For simplicity this type includes the network errors. Often it is not clear
     * what exactly the cause is. A timeout can be caused by an overloaded server,
     * or by a malformed request that takes too long to process, or by a dns server
     * not responding in time, or by the client being offline.
     */
    SERVER,

    /**
     * The client couldn't reach the server, or something.
     */
    NETWORK,

    /**
     * At the point where a problem was detected it was not possible to tell who's to blame.
//     */
    UNKNOWN,
    ;

    public static void assertSize(int expectedItems) {
        assert values().length == expectedItems : "Update the code calling this with " + expectedItems + "!";
    }

}

