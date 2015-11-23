package com.optimaize.anythingworks.server.common.fault;

import com.optimaize.anythingworks.common.fault.faultinfo.Blame;

/**
 * @author Fabian Kessler
 */
public class FaultInfoBuilders {

    public static class Client {
        public static FaultInfoBuilder.StepApplicationErrorCode accessDenied() {
            return FaultInfoBuilder.blame(Blame.CLIENT)
                    .faultCause("AccessDenied");
        }
        public static FaultInfoBuilder.StepApplicationErrorCode badRequest() {
            return FaultInfoBuilder.blame(Blame.CLIENT)
                    .faultCause("BadRequest");
        }
    }


    public static class Server {
        public static FaultInfoBuilder.StepApplicationErrorCode internalServerError() {
            return FaultInfoBuilder.blame(Blame.SERVER)
                    .faultCause("InternalServerError");
        }
        public static FaultInfoBuilder.StepApplicationErrorCode serviceUnavailable() {
            return FaultInfoBuilder.blame(Blame.SERVER)
                    .faultCause("ServiceUnavailable");
        }
    }

}
