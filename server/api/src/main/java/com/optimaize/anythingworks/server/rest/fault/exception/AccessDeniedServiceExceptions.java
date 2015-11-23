package com.optimaize.anythingworks.server.rest.fault.exception;

import com.optimaize.anythingworks.common.fault.exceptions.AccessDeniedServiceException;
import com.optimaize.anythingworks.server.common.fault.FaultInfos;
import com.optimaize.anythingworks.server.rest.fault.RestFaultInfoBuilder;
import org.jetbrains.annotations.NotNull;

import javax.ws.rs.core.Response;

/**
 *
 */
public class AccessDeniedServiceExceptions {

    /**
     * @return never returns.
     */
    public static AccessDeniedServiceException accountUnknown(@NotNull String userId) throws AccessDeniedServiceException {
        throw new AccessDeniedServiceException(
                RestFaultInfoBuilder.faultInfo(
                        FaultInfos.Client.AccessDenied.accountUnknown(userId),
                        Response.Status.UNAUTHORIZED
                )
        );
    }

    /**
     * @return never returns.
     */
    public static AccessDeniedServiceException hostBlocked(@NotNull String host) throws AccessDeniedServiceException {
        throw new AccessDeniedServiceException(
                RestFaultInfoBuilder.faultInfo(
                        FaultInfos.Client.AccessDenied.hostBlocked(host),
                        Response.Status.UNAUTHORIZED
                )
        );
    }

    /**
     * @return never returns.
     */
    public static AccessDeniedServiceException accountInactive(@NotNull String account, @NotNull String comment) throws AccessDeniedServiceException {
        throw new AccessDeniedServiceException(
                RestFaultInfoBuilder.faultInfo(
                        FaultInfos.Client.AccessDenied.accountInactive(account, comment),
                        Response.Status.FORBIDDEN
                )
        );
    }

    /**
     * @return never returns.
     */
    public static AccessDeniedServiceException networkRestriction(@NotNull String account, @NotNull String host) throws AccessDeniedServiceException {
        throw new AccessDeniedServiceException(
                RestFaultInfoBuilder.faultInfo(
                        FaultInfos.Client.AccessDenied.networkRestriction(account, host),
                        Response.Status.FORBIDDEN
                )
        );
    }

    /**
     * @return never returns.
     */
    public static AccessDeniedServiceException requestLimitExceeded(@NotNull String account, @NotNull String timePeriod) throws AccessDeniedServiceException {
        throw new AccessDeniedServiceException(
                RestFaultInfoBuilder.faultInfo(
                        FaultInfos.Client.AccessDenied.requestLimitReached(account, timePeriod),
                        Response.Status.FORBIDDEN
                )
        );
    }

    /**
     * @return never returns.
     */
    public static AccessDeniedServiceException tooManyConcurrentRequests(@NotNull String account) throws AccessDeniedServiceException {
        throw new AccessDeniedServiceException(
                RestFaultInfoBuilder.faultInfo(
                        FaultInfos.Client.AccessDenied.tooManyConcurrentRequests(account),
                        Response.Status.FORBIDDEN
                )
        );
    }

}
