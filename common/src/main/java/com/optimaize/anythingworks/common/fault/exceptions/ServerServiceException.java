package com.optimaize.anythingworks.common.fault.exceptions;

import com.optimaize.anythingworks.common.fault.faultinfo.FaultInfo;
import org.jetbrains.annotations.NotNull;

/**
 * Base class for exceptions thrown by web services where the Server (not the Client) is to blame.
 *
 * <p>Server exceptions can be thrown by the server only.</p>
 *
 * @author fab
 */
public class ServerServiceException extends ServiceException {

    public ServerServiceException(@NotNull FaultInfo faultInfo) {
        super(faultInfo);
    }

}
