package com.optimaize.anythingworks.client.soap.exception;

import org.jetbrains.annotations.NotNull;

/**
 * Thrown when there was an internal server exception that should not have happened.
 *
 * <p>In http terms this is a 500 status code.</p>
 *
 * <p>Possible causes:
 * <ul>
 *   <li>OutOfMemoryError on the server</li>
 *   <li>IllegalArgumentException somewhere deep down</li>
 *   <li>UnsupportedOperationException when a functionality has not been implemented yet but should have been</li>
 *   <li>AssertionError an unexpected situation</li>
 *   <li>A data access or IO exception on the server</li>
 * </ul></p>
 *
 * TODO for what name do we settle? InternalServiceException? InternalServerErrorServiceException? ServerErrorServiceException?
 *
 * @author fab
 */
public class InternalServiceException extends ServerServiceException {

    public InternalServiceException(String message, @NotNull FaultInfo faultInfo) {
        super(message, faultInfo);
    }

}