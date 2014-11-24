package com.optimaize.soapworks.server.exception;

import com.optimaize.soapworks.common.exception.Blame;

/**
 * Builder for the {@link FaultBean}.
 *
 * This builder enforces the concepts of the error code ranges etc.
 * If you don't like that, feel free to not use the builder at all or to overwrite the build() method.
 *
 * @author Fabian Kessler
 */
public class FaultBeanBuilder {

    public static StepFaultCause blame(Blame blame) {
        return new Builder(blame);
    }


    public interface StepFaultCause {
        StepErrorCode faultCause(String faultCause);
    }
    public interface StepErrorCode {
        StepMessage errorCode(int errorCode);
    }
    public interface StepMessage {
        StepRetrySameServer message(String message);
    }
    public interface StepRetrySameServer {
        StepRetryOtherServers retrySameServer(Retry retrySameServer);
    }
    public interface StepRetryOtherServers {
        StepProblemReported retryOtherServers(Retry retryOtherServers);
    }
    public interface StepProblemReported {
        FaultBean problemReported(boolean problemReported);
    }


    public static class Builder
            implements StepFaultCause,StepErrorCode,StepMessage,StepRetrySameServer,StepRetryOtherServers,StepProblemReported
    {

        private Blame blame;
        private String faultCause;
        private int errorCode;
        private String message;
        private Retry retrySameServer;
        private Retry retryOtherServers;
        private boolean problemReported = false;

        private Builder(Blame blame) {
            this.blame = blame;
        }

        @Override
        public Builder faultCause(String faultCause) {
            this.faultCause = faultCause;
            return this;
        }

        @Override
        public Builder errorCode(int errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        @Override
        public Builder message(String message) {
            this.message = message;
            return this;
        }

        @Override
        public Builder retrySameServer(Retry retrySameServer) {
            this.retrySameServer = retrySameServer;
            return this;
        }

        @Override
        public Builder retryOtherServers(Retry retryOtherServers) {
            this.retryOtherServers = retryOtherServers;
            return this;
        }

        @Override
        public FaultBean problemReported(boolean problemReported) {
            this.problemReported = problemReported;
            return build();
        }

        public FaultBean build() {
            Blame.assertSize(4);
            switch (blame) {
                case CLIENT:
                    if (errorCode < 2000 || errorCode >= 2999) throw new IllegalArgumentException("Client codes must be 2000-2999 but was: "+errorCode);
                    if (problemReported) throw new IllegalArgumentException("The flag problemReported can only be used when blame=SERVER!");
                    break;
                case SERVER:
                    if (errorCode < 1000 || errorCode >= 1999) throw new IllegalArgumentException("Server codes must be 1000-1999 but was: "+errorCode);
                    break;
                case NETWORK:
                    if (errorCode < 3000 || errorCode >= 3999) throw new IllegalArgumentException("Network codes must be 3000-3999 but was: "+errorCode);
                    if (problemReported) throw new IllegalArgumentException("The flag problemReported can only be used when blame=SERVER!");
                    break;
                case UNKNOWN:
                    if (errorCode < 4000 || errorCode >= 4999) throw new IllegalArgumentException("Network codes must be 4000-4999 but was: "+errorCode);
                    if (problemReported) throw new IllegalArgumentException("The flag problemReported can only be used when blame=SERVER!");
                    break;
                default:
                    throw new UnsupportedOperationException(blame.name());
            }

            return new FaultBean(
                    errorCode,
                    blame,
                    faultCause,
                    message,
                    retrySameServer,
                    retryOtherServers,
                    problemReported
            );
        }
    }

}
