package com.optimaize.anythingworks.server.common.exception;

import com.optimaize.anythingworks.common.fault.faultinfo.RetryType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * This version of the Retry class is for automatic SOAP marshalling within a {@link FaultBean}.
 */
public class Retry {

    @NotNull
    private RetryType retryType;

    @Nullable
    private Long retryInSeconds;

    public Retry(@NotNull RetryType retryType, @Nullable Long retryInSeconds) {
        this.retryType = retryType;
        this.retryInSeconds = retryInSeconds;
    }


    @NotNull
    public RetryType getRetryType() {
        return retryType;
    }

    @Nullable
    public Long getRetryInSeconds() {
        return retryInSeconds;
    }



    public Retry() {
    }
    public void setRetryType(@NotNull RetryType retryType) {
        this.retryType = retryType;
    }
    public void setRetryInSeconds(@Nullable Long retryInSeconds) {
        this.retryInSeconds = retryInSeconds;
    }
}
