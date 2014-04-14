package com.optimaize.soapworks.exampleproject.clientlib.lib;

import com.google.common.base.Optional;
import com.optimaize.command4j.Command;
import com.optimaize.command4j.ExecutionContext;
import com.optimaize.command4j.Mode;
import com.optimaize.command4j.ModeExtension;
import com.optimaize.soapworks.client.Keys;
import com.optimaize.soapworks.common.host.Host;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 *
 *
 * @deprecated
 * either move to soapworks, or remove, or whatever. not sure yet how and if at all to use this.
 * the urlbase and port-url-factory will be refactored into a url provider service anyway.
 * Also, if used at all, this extension must come first. otherwise other extensions won't find
 * values on the Mode when they are provided by this. That's all too much that can go wrong
 * for my taste. So rather have the user assemble a complete Mode himself, using factories or
 * whatever, and that's it.
 *
 *
 *
 * @author Eike Kettner
 */
@Deprecated
public class MyDefaultValuesExtension implements ModeExtension {

    @NotNull @Override
    public <A, R> Command<A, R> extend(@NotNull Command<A, R> cmd, @NotNull Mode mode) {
        return new DefaultValuesCommand<A, R>(cmd);
    }

    private static class DefaultValuesCommand<A, R> implements Command<A, R> {
        private final Command<A, R> delegate;

        private DefaultValuesCommand(Command<A, R> delegate) {
            this.delegate = delegate;
        }

        @Override @Nullable
        public R call(@NotNull Optional<A> arg, @NotNull ExecutionContext ec) throws Exception {
            Mode mode = ec.getMode();
            if (!mode.is(Keys.HOST)) {
                mode = ec.getMode().with(Keys.HOST, new Host("localhost",80));
            }
            if (!mode.is(Keys.PORT_URL_FACTORY)) {
                mode = mode.with(Keys.PORT_URL_FACTORY, new DemoappPortUrlFactory());
            }
            return ec.execute(delegate, mode, arg).orNull();
        }

        @Override
        public String toString() {
            return "DefaultValues(" + delegate.toString() + ")";
        }
    }
}
