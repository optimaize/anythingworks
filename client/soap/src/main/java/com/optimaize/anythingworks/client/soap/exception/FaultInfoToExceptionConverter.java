package com.optimaize.anythingworks.client.soap.exception;

import com.optimaize.anythingworks.common.fault.ErrorCodes;
import com.optimaize.anythingworks.common.fault.exceptions.*;
import com.optimaize.anythingworks.common.fault.faultinfo.Blame;
import com.optimaize.anythingworks.common.fault.faultinfo.Retry;
import com.optimaize.anythingworks.common.rest.fault.RestFaultInfo;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class FaultInfoToExceptionConverter {

    private static final Logger log = LoggerFactory.getLogger(FaultInfoToExceptionConverter.class);



    @NotNull
    public ServiceException makeException(@NotNull SoapFaultInfo faultInfo) {
        switch (faultInfo.getFaultCause()) {
            case "BadRequest":
                return new BadRequestServiceException(faultInfo);
            case "AccessDenied":
                return new AccessDeniedServiceException(faultInfo);

            case "InternalServerError":
                return new InternalServerErrorServiceException(faultInfo);
            case "ServiceTemporarilyUnavailable":
                return new ServiceTemporarilyUnavailableServiceException(faultInfo);
            case "BadResponse":
                return new BadResponseServiceException(faultInfo);

            default:
                //wasn't sure how to handle, but i think this is good.
                //logging error (as you like), and then trowing a protocol exception
                //with an overriding message, but keeping the original FaultInfo.

                String msg = "Unknown exception type from server: " + faultInfo.getFaultCause();
                log.error(msg);
                Retry retry;
                if (faultInfo.getBlame()==Blame.SERVER) {
                    //it is likely that the server can handle it next time, and there won't be an error at all.
                    //then there won't be a problem unmarshalling the error info either.
                    retry = Retry.now();
                } else {
                    //client error: no, because the client's data is wrong anyway, we just can't understand the detail
                    //              why it's wrong, but it's still wrong next time.
                    //              this could happen when the client uses an outdated protocol. server rejects it, and
                    //              the client can't even understand (the detail of) the response.
                    retry = Retry.no();
                }
                return new ClientServiceException(
                        new SoapFaultInfo(
                                "Protocol", Blame.CLIENT,
                                msg, ""+ ErrorCodes.Client.UNMARSHALLING_FAILED.getCode(),
                                null,
                                retry, retry
                        ), new RuntimeException("FaultInfo from server was: "+faultInfo.toString())
                );
        }
    }

}
