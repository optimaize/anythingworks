package com.optimaize.soapworks.client.soap.exception;

import com.optimaize.soapworks.common.host.Host;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.TimeUnit;

/**
 * Thrown when a request took longer than the permitted maximum time.
 *
 * @author fab
 */
public class TimeoutServiceException extends NetworkServiceException {

    @NotNull
    private final String serviceName;
    @NotNull
    private final Host host;
    private final int timeAmount;
    @NotNull
    private final TimeUnit timeUnit;

    public TimeoutServiceException(@NotNull String serviceName, @NotNull Host host, int timeAmount, @NotNull TimeUnit timeUnit) {
        this(null, serviceName, host, timeAmount, timeUnit);
    }
    public TimeoutServiceException(@Nullable Throwable cause, @NotNull String serviceName, @NotNull Host host, int timeAmount, @NotNull TimeUnit timeUnit) {
        super(cause, makeFault());
        this.serviceName = serviceName;
        this.host = host;
        this.timeAmount = timeAmount;
        this.timeUnit = timeUnit;
    }
    private static FaultInfo makeFault() {
        throw new UnsupportedOperationException();
//        return new FaultInfo(
//                Blame.SERVER,
//                Retry.NOW, //this is disputable, either NOW or LATER. in the best case the (network) problem is gone already. in the worst case the server is overloaded, and retrying now makes it worse.
//                false
//        );
    }


    @NotNull
    public String getServiceName() {
        return serviceName;
    }
    @NotNull
    public Host getHost() {
        return host;
    }
    public int getTimeAmount() {
        return timeAmount;
    }
    @NotNull
    public TimeUnit getTimeUnit() {
        return timeUnit;
    }
}
