package com.optimaize.soapworks.server.exception;

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
     * This is only available if <code>retryType</code> is {@link com.optimaize.soapworks.server.exception.RetryType#LATER}, and
     * can still be <code>null</code> if unknown.
     */
    @Nullable
    private Long retryInSeconds;

    private Retry(@NotNull RetryType retryType, @Nullable Long retryInSeconds) {
        this.retryType = retryType;
        this.retryInSeconds = retryInSeconds;
    }

    public static Retry no() {
        return new Retry(RetryType.NO, null);
    }
    public static Retry later(@Nullable Long retryInSeconds) {
        return new Retry(RetryType.LATER, retryInSeconds);
    }
    public static Retry now() {
        return new Retry(RetryType.NOW, null);
    }
    public static Retry unknown() {
        return new Retry(RetryType.UNKNOWN, null);
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
