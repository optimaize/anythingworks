package com.optimaize.soapworks.server.exception;

/**
 * If and when the client may retry and send the same request again.
 */
public enum RetryType {
    
    /**
     * It is useless to send the same request again.
     */
    NO,

    /**
     * The same request may be re-sent again, but later.
     */
    LATER,

    /**
     * The same request may be tried again now.
     */
    NOW,

    UNKNOWN

}
