package com.optimaize.anythingworks.server.soap.exception;

import org.jetbrains.annotations.NotNull;

/**
 * @author Fabian Kessler
 */
public class FaultBeans {

    public static class Client {

        public static class AccessDenied {

            public static FaultBean accountUnknown(@NotNull String userId) {
                return FaultBeanBuilders.Client.accessDenied()
                        .errorCode(ErrorCodes.Client.ACCOUNT_UNKNOWN.getCode())
                        .message("No such account: >>>" + userId + "<<<!")
                        .retrySameServer(Retry.no())
                        .retryOtherServers(Retry.no())
                        .problemReported(false);
            }

            public static FaultBean accountInactive(@NotNull String userId) {
                return FaultBeanBuilders.Client.accessDenied()
                        .errorCode(ErrorCodes.Client.ACCOUNT_INACTIVE.getCode())
                        .message("Account inactive: >>>" + userId + "<<<!")
                        .retrySameServer(Retry.no())
                        .retryOtherServers(Retry.no())
                        .problemReported(false);
            }
            public static FaultBean accountInactive(@NotNull String userId, @NotNull String comment) {
                return FaultBeanBuilders.Client.accessDenied()
                        .errorCode(ErrorCodes.Client.ACCOUNT_INACTIVE.getCode())
                        .message("Account inactive: >>>" + userId + "<<< with comment: "+comment)
                        .retrySameServer(Retry.no())
                        .retryOtherServers(Retry.no())
                        .problemReported(false);
            }

            public static FaultBean networkRestriction(@NotNull String userId, @NotNull String hostOrIp) {
                return FaultBeanBuilders.Client.accessDenied()
                        .errorCode(ErrorCodes.Client.ACCOUNT_INACTIVE.getCode())
                        .message("Access for user: >>>" + userId + "<<< not permitted from: >>>"+hostOrIp+"<<<!")
                        .retrySameServer(Retry.no())
                        .retryOtherServers(Retry.no())
                        .problemReported(false);
            }

            public static FaultBean requestLimitReached(@NotNull String userId, @NotNull String timePeriod) {
                return FaultBeanBuilders.Client.accessDenied()
                        .errorCode(ErrorCodes.Client.REQUEST_LIMIT_REACHED.getCode())
                        .message("Request limit exceeded for user >>>" + userId + "<<< and time period " + timePeriod + "!")
                        .retrySameServer(Retry.later(null))
                        .retryOtherServers(Retry.later(null))
                        .problemReported(false);
            }

            public static FaultBean tooManyConcurrentRequests(@NotNull String userId) {
                return FaultBeanBuilders.Client.accessDenied()
                        .errorCode(ErrorCodes.Client.TOO_MANY_CONCURRENT_REQUESTS.getCode())
                        .message("Too many concurrent requests for user: >>>" + userId + "<<<!")
                        .retrySameServer(Retry.later(null))
                        .retryOtherServers(Retry.later(null))
                        .problemReported(false);
            }

            public static FaultBean hostBlocked(@NotNull String message) {
                return FaultBeanBuilders.Client.accessDenied()
                        .errorCode(ErrorCodes.Client.HOST_BLOCKED.getCode())
                        .message(message)
                        .retrySameServer(Retry.later(null))
                        .retryOtherServers(Retry.later(null))
                        .problemReported(false);
            }

        }


        public static class InvalidInput {
            public static FaultBean invalidInput(@NotNull String message) {
                return FaultBeanBuilders.Client.invalidInput()
                        .errorCode(ErrorCodes.Client.INVALID_INPUT.getCode())
                        .message(message)
                        .retrySameServer(Retry.no())
                        .retryOtherServers(Retry.no())
                        .problemReported(false);
            }

            public static FaultBean invalidInput() {
                return FaultBeanBuilders.Client.invalidInput()
                        .errorCode(ErrorCodes.Client.INVALID_INPUT.getCode())
                        .message("Invalid input!")
                        .retrySameServer(Retry.no())
                        .retryOtherServers(Retry.no())
                        .problemReported(false);
            }
        }

    }


    public static class Server {

        public static class InternalServerError {
            public static FaultBean internalServerError() {
                return internalServerError("Internal server error!");
            }
            public static FaultBean internalServerError(@NotNull String message) {
                return FaultBeanBuilders.Server.internalServerError()
                        .errorCode(ErrorCodes.Server.SERVER_ERROR.getCode())
                        .message(message)
                        .retrySameServer(Retry.unknown())
                        .retryOtherServers(Retry.unknown())
                        .problemReported(false);
            }
        }

        public static class ServiceTemporarilyUnavailable {

            public static FaultBean serviceTemporarilyUnavailable() {
                return FaultBeanBuilders.Server.serviceUnavailable()
                        .errorCode(ErrorCodes.Server.SERVICE_TEMPORARILY_UNAVAILABLE.getCode())
                        .message("Service temporarily unavailable!")
                        .retrySameServer(Retry.later(null))
                        .retryOtherServers(Retry.now())
                        .problemReported(false);
            }
        }

    }

}