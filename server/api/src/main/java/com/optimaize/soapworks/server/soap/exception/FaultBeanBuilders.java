package com.optimaize.soapworks.server.soap.exception;

import com.optimaize.soapworks.common.soap.exception.Blame;

/**
 * @author Fabian Kessler
 */
public class FaultBeanBuilders {

    public static class Client {
        public static FaultBeanBuilder.StepErrorCode accessDenied() {
            return FaultBeanBuilder.blame(Blame.CLIENT)
                    .faultCause("AccessDenied");
        }
        public static FaultBeanBuilder.StepErrorCode invalidInput() {
            return FaultBeanBuilder.blame(Blame.CLIENT)
                    .faultCause("InvalidInput");
        }
    }


    public static class Server {
        public static FaultBeanBuilder.StepErrorCode internalServerError() {
            return FaultBeanBuilder.blame(Blame.SERVER)
                    .faultCause("InternalServerError");
        }
        public static FaultBeanBuilder.StepErrorCode serviceUnavailable() {
            return FaultBeanBuilder.blame(Blame.SERVER)
                    .faultCause("ServiceUnavailable");
        }
    }

}
