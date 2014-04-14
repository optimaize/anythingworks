package com.optimaize.soapworks.client.exception;

/**
 * Used in {@link FaultInfo}, tells if a service call may be retried after ending with a failure.
 *
 * @author fab
 */
public enum Retry {

    /**
     * It has no point to resend the same request again.
     * One use case is when the input arguments were illegal.
     */
    NO,

    /**
     * The same request may be sent to another server, or to the same server again later.
     * One use case is when the server is too busy.
     * Another is when the request limit has been reached.
     */
    LATER,

    /**
     * The same request may be sent again.
     * One use case is when there was a timeout.
     */
    NOW,

    /**
     * It is unknown what the problem was and thus also if a retry makes sense.
     */
    UNKNOWN

}
