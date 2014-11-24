package com.optimaize.soapworks.server.exception;

import com.optimaize.soapworks.common.exception.RetryType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Tells if and when the service request can be tried again in case of a failure.
 */
public class Retry {

    private static final Retry NO = new Retry(RetryType.NO, null);
    private static final Retry NOW = new Retry(RetryType.NOW, null);
    private static final Retry UNKNOWN = new Retry(RetryType.UNKNOWN, null);

    @NotNull
    private RetryType retryType;

    /**
     * Tells when the service can be called again.
     * This is only available if <code>retryType</code> is {@link com.optimaize.soapworks.common.exception.RetryType#LATER}, and
     * can still be <code>null</code> if unknown.
     */
    @Nullable
    private Long retryInSeconds;

    private Retry(@NotNull RetryType retryType, @Nullable Long retryInSeconds) {
        this.retryType = retryType;
        this.retryInSeconds = retryInSeconds;
    }

    public static Retry no() {
        return NO;
    }
    public static Retry later(@Nullable Long retryInSeconds) {
        return new Retry(RetryType.LATER, retryInSeconds);
    }
    public static Retry now() {
        return NOW;
    }
    public static Retry unknown() {
        return UNKNOWN;
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
