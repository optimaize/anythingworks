package com.optimaize.anythingworks.server.rest.fault;

import com.optimaize.anythingworks.common.fault.faultinfo.FaultInfo;
import com.optimaize.anythingworks.common.rest.fault.RestFaultInfo;
import org.jetbrains.annotations.NotNull;

import javax.ws.rs.core.Response;

/**
 *
 */
public class RestFaultInfoBuilder {

    @NotNull
    public static RestFaultInfo faultInfo(@NotNull FaultInfo faultInfo, Response.Status responseStatus) {
        return new RestFaultInfo(
                faultInfo.getFaultCause(),
                faultInfo.getBlame(),
                faultInfo.getMessage(),
                faultInfo.getApplicationErrorCode().orNull(),
                faultInfo.getIncidentId().orNull(),
                faultInfo.getRetrySameLocation().orNull(),
                faultInfo.getRetryOtherLocations().orNull(),
                responseStatus.getStatusCode(),
                responseStatus.getReasonPhrase()
        );
    }

    @NotNull
    public static StepHttpStatusCode faultInfo(@NotNull FaultInfo faultInfo) {
        return new Builder(faultInfo);
    }


    public interface StepHttpStatusCode {
        StepHttpStatusMeaning httpStatusCode(int httpStatusCode);
    }
    public interface StepHttpStatusMeaning {
        RestFaultInfo httpStatusMeaning(@NotNull String httpStatusMeaning);
    }



    public static class Builder
            implements StepHttpStatusCode,StepHttpStatusMeaning
    {

        @NotNull
        private FaultInfo faultInfo;
        private int httpStatusCode;
        private String httpStatusMeaning;

        private Builder(@NotNull FaultInfo faultInfo) {
            this.faultInfo = faultInfo;
        }

        @Override
        public Builder httpStatusCode(int httpStatusCode) {
            this.httpStatusCode = httpStatusCode;
            return this;
        }

        @Override
        public RestFaultInfo httpStatusMeaning(@NotNull String httpStatusMeaning) {
            this.httpStatusMeaning = httpStatusMeaning;
            return build();
        }


        private RestFaultInfo build() {
            return new RestFaultInfo(
                    faultInfo.getFaultCause(),
                    faultInfo.getBlame(),
                    faultInfo.getMessage(),
                    faultInfo.getApplicationErrorCode().orNull(),
                    faultInfo.getIncidentId().orNull(),
                    faultInfo.getRetrySameLocation().orNull(),
                    faultInfo.getRetryOtherLocations().orNull(),
                    httpStatusCode,
                    httpStatusMeaning
            );
        }


    }

}
