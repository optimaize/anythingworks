package com.optimaize.anythingworks.common.fault.faultinfo;

import com.google.common.base.Optional;
import com.optimaize.anythingworks.common.fault.exceptions.ServiceException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

/**
 * An object containing fault information that is used within a {@link ServiceException}.
 *
 * <p>It contains:
 * <ol>
 *   <li>{@link #blame} whether the server or the client is responsible</li>
 *   <li>{@link #applicationErrorCode} the reason for machines to understand</li>
 *   <li>{@link #message} the reason for humans to understand</li>
 *   <li>{@link #faultCause} exception class (technical detail)</li>
 *   <li>{@link #retrySameLocation} and {@link #retryOtherLocations} whether re-sending the same request makes sense</li>
 *   <li>{@link #incidentId} if it escalated for analysis by the service provider</li>
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
public class FaultInfo implements Serializable { //<- must be serializable, because exceptions are

    @NotNull
    private final String faultCause;

    /**
     */
    @NotNull
    private final Blame blame;

    @NotNull
    private final String message;

    @NotNull
    private final Optional<String> applicationErrorCode;

    @NotNull
    private final Optional<String> incidentId;


    @NotNull
    private final Optional<Retry> retrySameLocation;

    @NotNull
    private final Optional<Retry> retryOtherLocations;


    /**
     */
    public FaultInfo(
            @NotNull String faultCause,
            @NotNull Blame blame,
            @NotNull String message,
            @Nullable String applicationErrorCode,
            @Nullable String incidentId,
            @Nullable Retry retrySameLocation,
            @Nullable Retry retryOtherLocations
    ) {
        this.faultCause = faultCause;
        this.blame = blame;
        this.message = message;
        this.applicationErrorCode = Optional.fromNullable(applicationErrorCode);
        this.incidentId = Optional.fromNullable(incidentId);
        this.retrySameLocation = Optional.fromNullable(retrySameLocation);
        this.retryOtherLocations = Optional.fromNullable(retryOtherLocations);
    }

    /**
     * Exception class type.
     *
     * <p>Examples:
     * <pre>
     * Caused by Client:
     *  - BadRequest
     *  - AccessDenied
     *  - Protocol
     * Caused by Server:
     *  - InternalServerError
     *  - ServiceTemporarilyUnavailable
     *  - BadResponse
     *
     *
     *
     * Caused by Network:
     *  - Timeout
     *  - DNS lookup failed
     * </pre></p>
     *
     * <p>It is a String (not an enum) in order to be easily extendable.</p>
     */
    @NotNull
    public String getFaultCause() {
        return faultCause;
    }

    @NotNull
    public Blame getBlame() {
        return blame;
    }

    /**
     * Exception message for the human to understand the problem.
     *
     * <p>
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
    public String getMessage() {
        return message;
    }

    /**
     * An error code for machines to understand the problem.
     *
     * <p>It can be generic or specific.</p>
     *
     * <p>Applications may use this if they like, but don't need to. If they do then they
     * should document the meanings of codes.</p>
     */
    @NotNull
    public Optional<String> getApplicationErrorCode() {
        return applicationErrorCode;
    }

    /**
     * Tells if a server error was logged/reported/escalated for analyzing by a system admin, qa or programmer.
     * It is likely that only server errors generate an incident number for analyzing. Client errors like
     * invalid input, or security errors like no such account, are probably handled differently.
     */
    @NotNull
    public Optional<String> getIncidentId() {
        return incidentId;
    }

    /**
     * Tells if re-sending the same request that just failed with this error to the SAME NETWORK makes sense.
     * Absent if unknown.
     */
    @NotNull
    public Optional<Retry> getRetrySameLocation() {
        return retrySameLocation;
    }

    /**
     * Tells if re-sending the same request that just failed with this error to ANOTHER NETWORK makes sense.
     * Absent if unknown.
     */
    @NotNull
    public Optional<Retry> getRetryOtherLocations() {
        return retryOtherLocations;
    }


    @Override
    public String toString() {
        return "FaultInfo{" +
                "faultCause='" + faultCause + '\'' +
                ", blame=" + blame +
                ", message='" + message + '\'' +
                ", applicationErrorCode=" + applicationErrorCode +
                ", incidentId=" + incidentId +
                ", retrySameLocation=" + retrySameLocation +
                ", retryOtherLocations=" + retryOtherLocations +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FaultInfo faultInfo = (FaultInfo) o;

        if (!faultCause.equals(faultInfo.faultCause)) return false;
        if (blame != faultInfo.blame) return false;
        if (!message.equals(faultInfo.message)) return false;
        if (!applicationErrorCode.equals(faultInfo.applicationErrorCode)) return false;
        if (!incidentId.equals(faultInfo.incidentId)) return false;
        if (!retrySameLocation.equals(faultInfo.retrySameLocation)) return false;
        return retryOtherLocations.equals(faultInfo.retryOtherLocations);

    }

    @Override
    public int hashCode() {
        int result = faultCause.hashCode();
        result = 31 * result + blame.hashCode();
        result = 31 * result + message.hashCode();
        result = 31 * result + applicationErrorCode.hashCode();
        result = 31 * result + incidentId.hashCode();
        result = 31 * result + retrySameLocation.hashCode();
        result = 31 * result + retryOtherLocations.hashCode();
        return result;
    }
}
