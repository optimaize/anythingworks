package com.optimaize.soapworks.server.exception;

import org.jetbrains.annotations.NotNull;

/**
 *
 * Some of this information about the fault is normally present in an Exception.
 * But because we're dealing with web services we can't guarantee that the
 * client understands the concept of exceptions. And hence some information
 * is duplicated.
 *
 * @author Fabian Kessler
 */
public class FaultBean {

    private static final long serialVersionUID = 1L;


    /**
     * This is partly application-specific.
     * See the application's documentation for a complete list.
     *
     * ------------------------------------------------------------------
     * 1xxx = Server error
     *   1000 = Unspecified server error
     *   1100 = Internal server error
     *   1500 = Service unavailable
     *
     * ------------------------------------------------------------------
     * 2xxx = Client Error
     *   2000 = Unspecified Client Error
     *   2100 = Unspecified Permission Error
     *     2101 = No Such Account
     *     2120 = Request limit exceeded
     *     2121 = Too many concurrent requests
     *   2200 = Unspecified Invalid Input Error
     *   2300 = No such service
     *     2301 = No such service method
     *     2302 = No such service version
     *
     * ------------------------------------------------------------------
     * 3xxx = Network error
     *   3000 = Unspecified network error
     *
     * ------------------------------------------------------------------
     * 4xxx = Unknown error
     *   4000 = Unknown error
     *
     */
    private int errorCode;

    /**
     * From an exception point of view it's an ancestor of <code>faultCause</code>.
     */
    @NotNull
    private Blame blame;

    /**
     * Exception class name without suffix "WebServiceException".
     *
     * It is a String (not an enum) so that it can be extended easily, without the need of redeploying.
     *
     * Examples:
     *
     * InvalidInput
     * NoSuchService
     * AccessDenied
     *
     * InternalServerError
     * ServiceUnavailable
     *
     * NAME_RESOLUTION_FAILURE
     * Timeout
     *
     */
    @NotNull
    private String faultCause;

    /**
     * Exception message belonging to errorCode, possibly giving
     * more detail. Thus it's not a static string.
     * Examples:
     * "Internal Server Error" (static)
     * "Your account for user id 'blahblah' has expired on 'somedate'"
     */
    @NotNull
    private String message;

    @NotNull
    private Retry retrySameServer;
    @NotNull
    private Retry retryOtherServers;

    /**
     * Tells if a server error was logged/reported for analyzing by system admin etc.
     * Not security-wise (those are logged anyway).
     * This can only be true for server errors, not for client or network.
     */
    private boolean problemReported;


    public FaultBean(int errorCode,
                     @NotNull Blame blame,
                     @NotNull String faultCause,
                     @NotNull String message,
                     @NotNull Retry retrySameServer,
                     @NotNull Retry retryOtherServers,
                     boolean problemReported) {
        this.errorCode = errorCode;
        this.blame = blame;
        this.faultCause = faultCause;
        this.message = message;
        this.retrySameServer = retrySameServer;
        this.retryOtherServers = retryOtherServers;
        this.problemReported = problemReported;
    }



    /* generated getters */


    public int getErrorCode() {
        return errorCode;
    }

    @NotNull
    public Blame getBlame() {
        return blame;
    }

    @NotNull
    public String getFaultCause() {
        return faultCause;
    }

    @NotNull
    public String getMessage() {
        return message;
    }

    @NotNull
    public Retry getRetrySameServer() {
        return retrySameServer;
    }

    @NotNull
    public Retry getRetryOtherServers() {
        return retryOtherServers;
    }

    public boolean isProblemReported() {
        return problemReported;
    }




    public FaultBean() {
        //no-arg constructor required by ws spec.
    }


    /* generated setters */

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public void setBlame(@NotNull Blame blame) {
        this.blame = blame;
    }

    public void setFaultCause(@NotNull String faultCause) {
        this.faultCause = faultCause;
    }

    public void setMessage(@NotNull String message) {
        this.message = message;
    }

    public void setRetrySameServer(@NotNull Retry retrySameServer) {
        this.retrySameServer = retrySameServer;
    }

    public void setRetryOtherServers(@NotNull Retry retryOtherServers) {
        this.retryOtherServers = retryOtherServers;
    }

    public void setProblemReported(boolean problemReported) {
        this.problemReported = problemReported;
    }
}
