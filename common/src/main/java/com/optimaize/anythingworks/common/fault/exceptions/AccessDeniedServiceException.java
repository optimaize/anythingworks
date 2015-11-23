package com.optimaize.anythingworks.common.fault.exceptions;

import com.optimaize.anythingworks.common.fault.faultinfo.FaultInfo;
import org.jetbrains.annotations.NotNull;

/**
 * Thrown when the server denies access to the client.
 *
 * <p>Possible causes:
 * <ul>
 *   <li>invalid credentials</li>
 *   <li>account inactive, disabled, deleted</li>
 *   <li>out of credits</li>
 * </ul></p>
 *
 * @author fab
 */
public class AccessDeniedServiceException extends ClientServiceException {

    public AccessDeniedServiceException(@NotNull FaultInfo faultInfo) {
        super(faultInfo);
    }

}
