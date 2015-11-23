package com.optimaize.anythingworks.common.fault.faultinfo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;
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
     * This is only available if <code>retryType</code> is {@link RetryType#LATER}, and
     * can still be <code>absent</code> if unknown.
     */
    @Nullable
    private Optional<Long> retryInSeconds;

    public static Retry no() {
        return new Retry(RetryType.NO, null);
    }
    public static Retry later() {
        return new Retry(RetryType.LATER, null);
    }
    public static Retry later(@Nullable Long retryInSeconds) {
        return new Retry(RetryType.LATER, retryInSeconds);
    }
    public static Retry now() {
        return new Retry(RetryType.NOW, null);
    }

    @JsonCreator
    public Retry(
            @JsonProperty("retryType") @NotNull RetryType retryType,
            @JsonProperty("retryInSeconds") @Nullable Long retryInSeconds
    ) {
        this.retryType = retryType;
        this.retryInSeconds = Optional.fromNullable(retryInSeconds);
    }


    @NotNull
    public RetryType getRetryType() {
        return retryType;
    }

    @NotNull
    public Optional<Long> getRetryInSeconds() {
        return retryInSeconds;
    }


    @Override
    public String toString() {
        return "Retry{" +
                "retryType=" + retryType +
                ", retryInSeconds=" + retryInSeconds +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Retry retry = (Retry) o;

        if (retryType != retry.retryType) return false;
        return !(retryInSeconds != null ? !retryInSeconds.equals(retry.retryInSeconds) : retry.retryInSeconds != null);

    }

    @Override
    public int hashCode() {
        int result = retryType.hashCode();
        result = 31 * result + (retryInSeconds != null ? retryInSeconds.hashCode() : 0);
        return result;
    }
}
