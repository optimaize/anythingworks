package com.optimaize.anythingworks.common.rest.fault;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.optimaize.anythingworks.common.fault.faultinfo.Blame;
import com.optimaize.anythingworks.common.fault.faultinfo.FaultInfo;
import com.optimaize.anythingworks.common.fault.faultinfo.Retry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 *
 */
public class RestFaultInfo extends FaultInfo {

    /**
     * For example 500.
     */
    private final int httpStatusCode;
    /**
     * For example "Internal Server Error" for a 500.
     */
    @NotNull
    private final String httpStatusMeaning;


    @JsonCreator
    public RestFaultInfo(
            @JsonProperty("faultCause") @NotNull String faultCause,
            @JsonProperty("blame") @NotNull Blame blame,
            @JsonProperty("message") @NotNull String message,
            @JsonProperty("applicationErrorCode") @Nullable String applicationErrorCode,
            @JsonProperty("incidentId") @Nullable String incidentId,
            @JsonProperty("retrySameLocation") @Nullable Retry retrySameLocation,
            @JsonProperty("retryOtherLocations") @Nullable Retry retryOtherLocations,
            @JsonProperty("httpStatusCode") int httpStatusCode,
            @JsonProperty("httpStatusMeaning") @NotNull String httpStatusMeaning
    ) {
        super(faultCause, blame, message, applicationErrorCode, incidentId, retrySameLocation, retryOtherLocations);
        this.httpStatusCode = httpStatusCode;
        this.httpStatusMeaning = httpStatusMeaning;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    @NotNull
    public String getHttpStatusMeaning() {
        return httpStatusMeaning;
    }
}
