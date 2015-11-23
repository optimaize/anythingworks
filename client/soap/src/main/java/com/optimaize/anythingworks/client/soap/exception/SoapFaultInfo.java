package com.optimaize.anythingworks.client.soap.exception;

import com.optimaize.anythingworks.common.fault.faultinfo.FaultInfo;
import com.optimaize.anythingworks.common.fault.faultinfo.Retry;
import com.optimaize.anythingworks.common.fault.faultinfo.Blame;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * An object containing fault information according to the SOAP specification which
 * makes up the {@code Fault} element of the message {@code Body}.
 *
 * <p>It contains:
 * <ol>
 *   <li>{@link #faultCause} exception class (technical detail)</li>
 * </ol>
 * </p>
 *
 * @author Fabian Kessler
 */
public class SoapFaultInfo extends FaultInfo {

    public SoapFaultInfo(@NotNull String faultCause, @NotNull Blame blame, @NotNull String message, @Nullable String applicationErrorCode, @Nullable String incidentId, @Nullable Retry retrySameLocation, @Nullable Retry retryOtherLocations) {
        super(faultCause, blame, message, applicationErrorCode, incidentId, retrySameLocation, retryOtherLocations);
    }
}
