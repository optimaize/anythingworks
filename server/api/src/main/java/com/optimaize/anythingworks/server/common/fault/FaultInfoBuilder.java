package com.optimaize.anythingworks.server.common.fault;

import com.optimaize.anythingworks.common.fault.faultinfo.Blame;
import com.optimaize.anythingworks.common.fault.faultinfo.FaultInfo;
import org.jetbrains.annotations.Nullable;

/**
 * Builder for the {@link com.optimaize.anythingworks.common.fault.faultinfo.FaultInfo}.
 *
 * If you don't like the behavior, feel free to not use the builder at all or to overwrite the build() method.
 *
 * @author Fabian Kessler
 */
public class FaultInfoBuilder {

    public static StepFaultCause blame(Blame blame) {
        return new Builder(blame);
    }


    public interface StepFaultCause {
        StepApplicationErrorCode faultCause(String faultCause);
    }
    public interface StepApplicationErrorCode {
        StepMessage applicationErrorCode(@Nullable String applicationErrorCode);
    }
    public interface StepMessage {
        StepRetrySameLocation message(String message);
    }
    public interface StepRetrySameLocation {
        StepRetryOtherLocations retrySameLocation(com.optimaize.anythingworks.common.fault.faultinfo.Retry retrySameLocation);
    }
    public interface StepRetryOtherLocations {
        StepIncidentId retryOtherLocations(com.optimaize.anythingworks.common.fault.faultinfo.Retry retryOtherLocations);
    }
    public interface StepIncidentId {
        FaultInfo incidentId(String incidentId);
    }


    public static class Builder
            implements StepFaultCause,StepApplicationErrorCode,StepMessage,StepRetrySameLocation,StepRetryOtherLocations,StepIncidentId
    {

        private Blame blame;
        private String faultCause;
        private String applicationErrorCode;
        private String message;
        private com.optimaize.anythingworks.common.fault.faultinfo.Retry retrySameLocation;
        private com.optimaize.anythingworks.common.fault.faultinfo.Retry retryOtherLocations;
        private String incidentId;

        private Builder(Blame blame) {
            this.blame = blame;
        }

        @Override
        public Builder faultCause(String faultCause) {
            this.faultCause = faultCause;
            return this;
        }

        @Override
        public Builder applicationErrorCode(@Nullable String applicationErrorCode) {
            this.applicationErrorCode = applicationErrorCode;
            return this;
        }

        @Override
        public Builder message(String message) {
            this.message = message;
            return this;
        }

        @Override
        public Builder retrySameLocation(com.optimaize.anythingworks.common.fault.faultinfo.Retry retrySameLocation) {
            this.retrySameLocation = retrySameLocation;
            return this;
        }

        @Override
        public Builder retryOtherLocations(com.optimaize.anythingworks.common.fault.faultinfo.Retry retryOtherLocations) {
            this.retryOtherLocations = retryOtherLocations;
            return this;
        }

        @Override
        public FaultInfo incidentId(String incidentId) {
            this.incidentId = incidentId;
            return build();
        }

        private FaultInfo build() {
            return new FaultInfo(
                    faultCause,
                    blame,
                    message,
                    applicationErrorCode,
                    incidentId,
                    retrySameLocation,
                    retryOtherLocations
            );
        }
    }

}
