package com.optimaize.soapworks.client.exception;

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
public class InvalidInputServiceException extends ServiceException {

    public InvalidInputServiceException(String message, @NotNull FaultInfo faultInfo) {
        super(message, faultInfo);
    }

}
