package com.optimaize.anythingworks.client.soap.exensions.autoretry;

import com.optimaize.anythingworks.common.fault.exceptions.ServiceException;
import com.optimaize.anythingworks.common.fault.faultinfo.FaultInfo;
import com.optimaize.command4j.ext.extensions.failover.autoretry.AutoRetryStrategy;
import com.optimaize.command4j.lang.Duration;
import com.optimaize.anythingworks.common.fault.faultinfo.RetryType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Strategy that retries if the exception says that a retry may be done now.
 * The duration is set to 1000ms to wait before doing it.
 */
public class DefaultAutoRetryStrategy implements AutoRetryStrategy {
    @Override @Nullable
    public Duration doRetry(int executionCounter, @NotNull Exception exception) {
            if (executionCounter==1) {
                if (exception instanceof ServiceException) {
                    FaultInfo faultInfo = ((ServiceException) exception).getFaultInfo();
                    if (faultInfo.getRetrySameLocation().isPresent() && faultInfo.getRetrySameLocation().get().getRetryType() == RetryType.NOW) {
                        return Duration.millis(1000);
                    }
                }
            }
            return null;
    }
}
