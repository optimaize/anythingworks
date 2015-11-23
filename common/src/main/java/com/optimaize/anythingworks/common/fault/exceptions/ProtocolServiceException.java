package com.optimaize.anythingworks.common.fault.exceptions;

import com.optimaize.anythingworks.common.fault.faultinfo.FaultInfo;
import org.jetbrains.annotations.NotNull;

/**
 *
 */
public class ProtocolServiceException extends ClientServiceException {

    public ProtocolServiceException(@NotNull FaultInfo faultInfo) {
        super(faultInfo);
    }

}
