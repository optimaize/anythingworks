package com.optimaize.anythingworks.server.common.exception;

import com.optimaize.anythingworks.common.fault.faultinfo.Blame;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Like FaultInfo, but for automatic SOAP marshalling.
 *
 * @author Fabian Kessler
 */
public class FaultBean {

    private static final long serialVersionUID = 1L;


    @NotNull
    private String faultCause;

    /**
     */
    @NotNull
    private Blame blame;

    @NotNull
    private String message;

    @Nullable
    private String applicationErrorCode;

    @Nullable
    private String incidentId;


    @Nullable
    private Retry retrySameLocation;

    @Nullable
    private Retry retryOtherLocations;


    public FaultBean(@NotNull String faultCause, @NotNull Blame blame, @NotNull String message, String applicationErrorCode, String incidentId, Retry retrySameLocation, Retry retryOtherLocations) {
        this.faultCause = faultCause;
        this.blame = blame;
        this.message = message;
        this.applicationErrorCode = applicationErrorCode;
        this.incidentId = incidentId;
        this.retrySameLocation = retrySameLocation;
        this.retryOtherLocations = retryOtherLocations;
    }


    @NotNull
    public String getFaultCause() {
        return faultCause;
    }

    @NotNull
    public Blame getBlame() {
        return blame;
    }

    @NotNull
    public String getMessage() {
        return message;
    }

    @Nullable
    public String getApplicationErrorCode() {
        return applicationErrorCode;
    }

    @Nullable
    public String getIncidentId() {
        return incidentId;
    }

    @Nullable
    public Retry getRetrySameLocation() {
        return retrySameLocation;
    }

    @Nullable
    public Retry getRetryOtherLocations() {
        return retryOtherLocations;
    }




    public FaultBean() {
    }

    public void setFaultCause(@NotNull String faultCause) {
        this.faultCause = faultCause;
    }

    public void setBlame(@NotNull Blame blame) {
        this.blame = blame;
    }

    public void setMessage(@NotNull String message) {
        this.message = message;
    }

    public void setApplicationErrorCode(@Nullable String applicationErrorCode) {
        this.applicationErrorCode = applicationErrorCode;
    }

    public void setIncidentId(@Nullable String incidentId) {
        this.incidentId = incidentId;
    }

    public void setRetrySameLocation(@Nullable Retry retrySameLocation) {
        this.retrySameLocation = retrySameLocation;
    }

    public void setRetryOtherLocations(@Nullable Retry retryOtherLocations) {
        this.retryOtherLocations = retryOtherLocations;
    }
}
