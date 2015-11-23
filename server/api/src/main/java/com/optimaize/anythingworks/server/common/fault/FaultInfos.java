package com.optimaize.anythingworks.server.common.fault;

import com.optimaize.anythingworks.common.fault.ErrorCodes;
import com.optimaize.anythingworks.common.fault.faultinfo.FaultInfo;
import com.optimaize.anythingworks.common.fault.faultinfo.Retry;
import org.jetbrains.annotations.NotNull;

/**
 * @author Fabian Kessler
 */
public class FaultInfos {

    public static class Client {

        public static class AccessDenied {

            public static FaultInfo accountUnknown(@NotNull String userId) {
                return FaultInfoBuilders.Client.accessDenied()
                        .applicationErrorCode(""+ ErrorCodes.Client.ACCOUNT_UNKNOWN.getCode())
                        .message("No such account: >>>" + userId + "<<<!")
                        .retrySameLocation(Retry.no())
                        .retryOtherLocations(Retry.no())
                        .incidentId(null);
            }

            public static FaultInfo accountInactive(@NotNull String userId) {
                return FaultInfoBuilders.Client.accessDenied()
                        .applicationErrorCode(""+ErrorCodes.Client.ACCOUNT_INACTIVE.getCode())
                        .message("Account inactive: >>>" + userId + "<<<!")
                        .retrySameLocation(Retry.no())
                        .retryOtherLocations(Retry.no())
                        .incidentId(null);
            }
            public static FaultInfo accountInactive(@NotNull String userId, @NotNull String comment) {
                return FaultInfoBuilders.Client.accessDenied()
                        .applicationErrorCode(""+ErrorCodes.Client.ACCOUNT_INACTIVE.getCode())
                        .message("Account inactive: >>>" + userId + "<<< with comment: "+comment)
                        .retrySameLocation(Retry.no())
                        .retryOtherLocations(Retry.no())
                        .incidentId(null);
            }

            public static FaultInfo networkRestriction(@NotNull String userId, @NotNull String hostOrIp) {
                return FaultInfoBuilders.Client.accessDenied()
                        .applicationErrorCode(""+ErrorCodes.Client.ACCOUNT_INACTIVE.getCode())
                        .message("Access for user: >>>" + userId + "<<< not permitted from: >>>"+hostOrIp+"<<<!")
                        .retrySameLocation(Retry.no())
                        .retryOtherLocations(Retry.no())
                        .incidentId(null);
            }

            public static FaultInfo requestLimitReached(@NotNull String userId, @NotNull String timePeriod) {
                return FaultInfoBuilders.Client.accessDenied()
                        .applicationErrorCode(""+ErrorCodes.Client.REQUEST_LIMIT_REACHED.getCode())
                        .message("Request limit exceeded for user >>>" + userId + "<<< and time period " + timePeriod + "!")
                        .retrySameLocation(Retry.later(null))
                        .retryOtherLocations(Retry.later(null))
                        .incidentId(null);
            }

            public static FaultInfo tooManyConcurrentRequests(@NotNull String userId) {
                return FaultInfoBuilders.Client.accessDenied()
                        .applicationErrorCode(""+ErrorCodes.Client.TOO_MANY_CONCURRENT_REQUESTS.getCode())
                        .message("Too many concurrent requests for user: >>>" + userId + "<<<!")
                        .retrySameLocation(Retry.later(null))
                        .retryOtherLocations(Retry.later(null))
                        .incidentId(null);
            }

            public static FaultInfo hostBlocked(@NotNull String host) {
                return FaultInfoBuilders.Client.accessDenied()
                        .applicationErrorCode(""+ErrorCodes.Client.HOST_BLOCKED.getCode())
                        .message("Host blocked: "+host)
                        .retrySameLocation(Retry.later(null))
                        .retryOtherLocations(Retry.later(null))
                        .incidentId(null);
            }

        }


        public static class BadRequest {
            public static FaultInfo invalidInput(@NotNull String message) {
                return FaultInfoBuilders.Client.badRequest()
                        .applicationErrorCode(""+ErrorCodes.Client.INVALID_INPUT.getCode())
                        .message(message)
                        .retrySameLocation(Retry.no())
                        .retryOtherLocations(Retry.no())
                        .incidentId(null);
            }

            public static FaultInfo invalidInput() {
                return FaultInfoBuilders.Client.badRequest()
                        .applicationErrorCode(""+ErrorCodes.Client.INVALID_INPUT.getCode())
                        .message("Invalid input!")
                        .retrySameLocation(Retry.no())
                        .retryOtherLocations(Retry.no())
                        .incidentId(null);
            }
        }

    }


    public static class Server {

        public static class InternalServerError {
            public static FaultInfo internalServerError() {
                return internalServerError("Internal server error!");
            }
            public static FaultInfo internalServerError(@NotNull String message) {
                return FaultInfoBuilders.Server.internalServerError()
                        .applicationErrorCode(""+ErrorCodes.Server.INTERNAL_SERVER_ERROR.getCode())
                        .message(message)
                        .retrySameLocation(null)
                        .retryOtherLocations(null)
                        .incidentId(null);
            }
        }

        public static class ServiceTemporarilyUnavailable {

            public static FaultInfo serviceTemporarilyUnavailable() {
                return FaultInfoBuilders.Server.serviceUnavailable()
                        .applicationErrorCode(""+ErrorCodes.Server.SERVICE_TEMPORARILY_UNAVAILABLE.getCode())
                        .message("Service temporarily unavailable!")
                        .retrySameLocation(Retry.later(null))
                        .retryOtherLocations(Retry.now())
                        .incidentId(null);
            }
        }

    }

}