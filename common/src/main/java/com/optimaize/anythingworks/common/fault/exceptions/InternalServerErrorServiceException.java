package com.optimaize.anythingworks.common.fault.exceptions;

import com.optimaize.anythingworks.common.fault.faultinfo.FaultInfo;
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
 *   <li>NoClassDefFoundError and NoSuchMethodError (a broken software build, usually outdated dependency)</li>
 * </ul></p>
 *
 * @author fab
 */
public class InternalServerErrorServiceException extends ServerServiceException {

    public InternalServerErrorServiceException(@NotNull FaultInfo faultInfo) {
        super(faultInfo);
    }

}