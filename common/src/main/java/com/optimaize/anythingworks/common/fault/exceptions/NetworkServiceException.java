package com.optimaize.anythingworks.common.fault.exceptions;

import com.optimaize.anythingworks.common.fault.faultinfo.FaultInfo;
import org.jetbrains.annotations.NotNull;

/**
 * @author fab
 */
public class NetworkServiceException extends ServiceException {

    public NetworkServiceException(@NotNull FaultInfo faultInfo) {
        super(faultInfo);
    }

}
