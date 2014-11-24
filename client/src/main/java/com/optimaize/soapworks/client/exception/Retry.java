package com.optimaize.soapworks.client.exception;

import com.optimaize.soapworks.common.exception.RetryType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Tells if and when the service request can be tried again in case of a failure.
 */
public class Retry {

    @NotNull
    private RetryType retryType;

    /**
     * Tells when the service can be called again.
     * This is only available if <code>retryType</code> is {@link com.optimaize.soapworks.common.exception.RetryType#LATER}, and
     * can still be <code>null</code> if unknown.
     */
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


    @Override
    public String toString() {
        return "Retry{" +
                "retryType=" + retryType +
                ", retryInSeconds=" + retryInSeconds +
                '}';
    }
}
