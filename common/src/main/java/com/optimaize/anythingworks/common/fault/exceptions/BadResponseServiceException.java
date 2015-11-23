package com.optimaize.anythingworks.common.fault.exceptions;

import com.optimaize.anythingworks.common.fault.faultinfo.FaultInfo;
import org.jetbrains.annotations.NotNull;

/**
 * Thrown when the server did not adhere to the contract.
 *
 * <p>Possible causes:
 * <ul>
 *   <li>client requests JSON, server doesn't send that</li>
 * </ul></p>
 *
 * @author fab
 */
public class BadResponseServiceException extends ServerServiceException {

    public BadResponseServiceException(@NotNull FaultInfo faultInfo) {
        super(faultInfo);
    }

}
