package com.optimaize.anythingworks.common.fault.exceptions;

import com.optimaize.anythingworks.common.fault.faultinfo.FaultInfo;
import org.jetbrains.annotations.NotNull;

/**
 * Thrown when either the server or the client rejects the user input.
 *
 * <p>Possible causes:
 * <ul>
 *   <li>illegal argument</li>
 *   <li>incomplete request</li>
 *   <li>
 *       wrong api use
 *       <ul>
 *           <li>illegal state</li>
 *           <li>senseless request (for example when asking for the gender when providing it already)</li>
 *       </ul>
 *   </li>
 * </ul></p>
 *
 * @author fab
 */
public class BadRequestServiceException extends ClientServiceException {

    public BadRequestServiceException(@NotNull FaultInfo faultInfo) {
        super(faultInfo);
    }

    public BadRequestServiceException(@NotNull FaultInfo faultInfo, Throwable throwable) {
        super(faultInfo, throwable);
    }


}
