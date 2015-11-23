package com.optimaize.anythingworks.server.rest.fault;

import com.optimaize.anythingworks.server.common.exception.FaultBean;
import com.optimaize.anythingworks.server.common.exception.Retry;
import com.optimaize.anythingworks.common.rest.fault.RestFaultInfo;
import org.jetbrains.annotations.NotNull;

import javax.ws.rs.core.Response;

/**
 *
 */
@Deprecated
public class FaultUtil {

    @NotNull
    public static RestFaultInfo wrap(FaultBean faultBean, int httpStatusCode, String httpStatusMeaning) {
        return new RestFaultInfo(
                faultBean.getFaultCause(),
                faultBean.getBlame(),
                faultBean.getMessage(),
                faultBean.getApplicationErrorCode(),
                faultBean.getIncidentId(),
                convert(faultBean.getRetrySameLocation()),
                convert(faultBean.getRetryOtherLocations()),
                httpStatusCode,
                httpStatusMeaning
        );
    }

    @NotNull
    public static RestFaultInfo wrap(FaultBean faultBean, Response.Status httpStatus) {
        return wrap(
                faultBean,
                httpStatus.getStatusCode(),
                httpStatus.getReasonPhrase()
        );
    }

    private static com.optimaize.anythingworks.common.fault.faultinfo.Retry convert(Retry retrySameLocation) {
        return new com.optimaize.anythingworks.common.fault.faultinfo.Retry(
                retrySameLocation.getRetryType(),
                retrySameLocation.getRetryInSeconds()
        );
    }


}
