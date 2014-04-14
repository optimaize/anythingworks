package com.optimaize.soapworks.server.exception;

/**
 * Tells who's likely to blame when exceptions occur.
 * <p>Based on this, decisions like auto-retry and logging can be made.</p>
 */
public enum Blame {

    CLIENT, SERVER, NETWORK, UNKNOWN


//    /**
//     * A problem on the server which is not one of the other SERVER_ kinds.
//     * Example: an IllegalArgumentException somewhere deep inside server code.
//     */
//    SERVER_UNKNOWN(true, true),
//    /**
//     * A problem on the server in the code.
//     */
//    SERVER_CODE(true, true),
//    /**
//     * A problem on the server in the data.
//     */
//    SERVER_DATA(true, true),
//    /**
//     * A problem on the server that may be just temporary.
//     */
//    SERVER_CIRCUMSTANCE(false, true),
//    /**
//     * The server is busy or takes too long to execute.
//     */
//    SERVER_PERFORMANCE(false, false),
//
//    /**
//     * The client sent invalid data.
//     * Example: an InvalidInputWebServiceException thrown early, when checking input.
//     */
//    CLIENT_DATA(true, false),
//    /**
//     * For example when the client sends a request that should never have been sent.
//     */
//    CLIENT_LOGIC(true, false),
//    /**
//     * For example when the client sends more concurrent requests than permitted.
//     */
//    CLIENT_BEHAVIOR(true, false),
//    /**
//     * Programming errors on the client side.
//     */
//    CLIENT_CODE(true, false),
//
//    /**
//     * Such as network timeouts or unresolvable hosts.
//     */
//    NETWORK(false, false),
//
//    /**
//     * When it's not possible to say who's to blame.
//     * DON'T USE THIS unless really necessary. think first.
//     */
//    UNKNOWN(false, false);



//    private final boolean persistent;
//    private final boolean doLog;
//
//    Blame(boolean persistent, boolean doLog) {
//        this.persistent = persistent;
//        this.doLog = doLog;
//    }
//
//    public boolean doLog() {
//        return doLog;
//    }

}

