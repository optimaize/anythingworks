package com.optimaize.anythingworks.common.fault.exceptions;

import com.optimaize.anythingworks.common.fault.faultinfo.FaultInfo;
import org.jetbrains.annotations.NotNull;

/**
 * Base class for exceptions thrown by web services where the Client (not the Server) is to blame.
 *
 * <p>Client exceptions can be thrown by the server and the client.</p>
 *
 * @author fab
 */
public class ClientServiceException extends ServiceException {

    public ClientServiceException(@NotNull FaultInfo faultInfo) {
        super(faultInfo);
    }

    public ClientServiceException(@NotNull FaultInfo faultInfo, Throwable throwable) {
        super(faultInfo, throwable);
    }


}
