package com.optimaize.soapworks.server.exception;

import org.jetbrains.annotations.NotNull;

/**
 * An object containing fault information according to the SOAP specification which
 * makes up the {@code Fault} element of the message {@code Body}.
 *
 * <p>It contains:
 * <ol>
 *   <li>{@link #blame} whether the server or the client is responsible</li>
 *   <li>{@link #errorCode} the reason for machines to understand</li>
 *   <li>{@link #message} the reason for humans to understand</li>
 *   <li>{@link #faultCause} exception class (technical detail)</li>
 *   <li>{@link #retrySameServer} and {@link #retryOtherServers} whether re-sending the same request makes sense</li>
 *   <li>{@link #problemReported} if it escalated for analysis by the service provider</li>
 * </ol>
 * </p>
 *
 * <p>Some of this information about the fault is normally present in an Exception.
 * But because we're dealing with web services we can't guarantee that the
 * client understands the concept of exceptions. And hence some information
 * is duplicated.</p>
 *
 * @author Fabian Kessler
 */
public class FaultBean {

    private static final long serialVersionUID = 1L;


    /**
     * An error code for machines to understand the problem.
     * It can be generic or specific.
     *
     * <p>This is partly application-specific.
     * See the application's documentation for a complete list.
     * The recommendation is to use the following code ranges:
     * <pre>
     * 1xxx = Client Error
     * 2xxx = Server error
     * 3xxx = Network error
     * 4xxx = Unknown error
     * </pre></p>
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
     * <p>Examples:
     * <pre>
     * Caused by Client:
     *  - InvalidInput
     *  - NoSuchService
     *  - AccessDenied
     * Caused by Server:
     *  - InternalServerError
     *  - ServiceUnavailable
     * Caused by Network:
     *  - Timeout
     *  - DNS lookup failed
     * </pre></p>
     *
     * <p>It is a String (not an enum) in order to be easily extendable.</p>
     */
    @NotNull
    private String faultCause;

    /**
     * Exception message for the human to understand the problem.
     *
     * <p>It has to match the basic meaning of the {@code errorCode}.
     * It can be generic or specific.
     * <pre>Examples:
     *  - "Account expired"
     *  - "Your account for user id 'foo' has expired on '2014-12-31'"
     * </pre></p>
     *
     * <p>It can be generic because either no detailed info is available, or because the system prefers to hide it
     * from the end user.</p>
     */
    @NotNull
    private String message;

    /**
     * Tells if re-sending the same request that just failed with this error to the SAME NETWORK makes sense.
     */
    @NotNull
    private Retry retrySameServer;
    /**
     * Tells if re-sending the same request that just failed with this error to ANOTHER NETWORK makes sense.
     */
    @NotNull
    private Retry retryOtherServers;

    /**
     * Tells if a server error was logged/reported/escalated for analyzing by a system admin, qa or programmer.
     * This can only be true for server errors, not for client or network.
     * Security errors (no such account etc) are meant to be logged separately anyway.
     */
    private boolean problemReported;


    /**
     * Use the {@link FaultBeanBuilder}.
     */
    protected FaultBean(int errorCode,
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



    @Override
    public String toString() {
        return "FaultBean{" +
                "errorCode=" + errorCode +
                ", blame=" + blame +
                ", faultCause='" + faultCause + '\'' +
                ", message='" + message + '\'' +
                ", retrySameServer=" + retrySameServer +
                ", retryOtherServers=" + retryOtherServers +
                ", problemReported=" + problemReported +
                '}';
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
