package com.optimaize.soapworks.client.exensions;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.optimaize.command4j.*;
import com.optimaize.command4j.commands.BaseCommand;
import com.optimaize.command4j.lang.Key;
import com.optimaize.soapworks.client.Keys;
import com.optimaize.soapworks.common.host.Host;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * Counts the number of pending requests per {@link Host} and blocks new command executions
 * until a slot becomes available.
 *
 * <p/>
 * <p>Configuration:
 * <pre><code>
 *     .with(MaxConcurrentRequestsPerHostExtension.SLOTS, 10)
 * </code></pre>
 * </p>
 *
 * TODO fab: currently this throws instead of blocking. make configurable.
 *
 * @author Fabian Kessler
 */
public class MaxConcurrentRequestsPerHostExtension implements ModeExtension {

    /**
     */
    public static final Key<Integer> SLOTS = Key.integerKey("maxConcurrentRequestPerHostSlots");

    @NotNull @Override
    public <A, R> Command<A, R> extend(@NotNull final Command<A, R> cmd, @NotNull final Mode mode) {
        return mode.get(SLOTS).transform(new Function<Integer, Command<A, R>>() {
            @Override
            public Command<A, R> apply(Integer slots) {
                if (slots != null && slots > 0) {
                    Host host = mode.get(Keys.HOST).get();
                    return new Interceptor<A, R>(cmd, slots, host);
                }
                return cmd;
            }
        }).or(cmd);
    }

    public static class Interceptor<A, R> extends BaseCommand<A, R> {
        //todo eike: decide with fab how we handle this. static = global for the whole jvm,
        //otherwise with multiple instances of this extension (happens) it would be per instance.
        //it must be very clear to the user how it behaves, and how it's configurable (if at all).
        private static final Map<Host, Integer> numOpen = new HashMap<>();
        @NotNull
        private final Command<A, R> delegate;
        private final int slots;
        @NotNull
        private final Host host;

        public Interceptor(@NotNull Command<A, R> delegate, int slots, @NotNull Host host) {
            this.delegate = delegate;
            this.slots = slots;
            this.host = host;
        }

        @Override
        public R call(@NotNull Optional<A> arg, @NotNull ExecutionContext ec) throws Exception {
            try {
                beforeRequest(host);
                return delegate.call(arg, ec);
            } finally {
                afterRequest(host);
            }
        }

        private synchronized void beforeRequest(@NotNull Host host) {
            Integer integer = numOpen.get(host);
            if (integer==null) integer=0;
            if (integer >= slots) {
                throw new RuntimeException("Too many pending requests for host "+host+" ("+numOpen+" open)!");
            }
            numOpen.put(host, integer+1);
        }

        private synchronized void afterRequest(@NotNull Host host) {
            Integer integer = numOpen.get(host);
            if (integer==null || integer<1) {
                throw new IllegalStateException("Programming error: at least one request must be open, value was: "+integer+"!");
            }
            numOpen.put(host, integer-1);
        }

        @Override
        public String toString() {
            return "MaxConcurrentRequests(" + delegate.toString() + ")";
        }
    }
}
