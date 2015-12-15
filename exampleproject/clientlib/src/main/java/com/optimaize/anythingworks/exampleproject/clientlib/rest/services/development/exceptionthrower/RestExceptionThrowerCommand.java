package com.optimaize.anythingworks.exampleproject.clientlib.rest.services.development.exceptionthrower;

import com.google.common.base.Optional;
import com.optimaize.anythingworks.client.rest.RestBaseCommand;
import com.optimaize.anythingworks.exampleproject.clientlib.DemoappKeys;
import com.optimaize.command4j.ExecutionContext;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Callable;

/**
 * This command tells the server to throw certain exceptions.
 * It can also be at random, meaning it succeeds sometimes and fails other times.
 */
public class RestExceptionThrowerCommand extends RestBaseCommand<Port, ExceptionThrowerParams, String> {

    private static final String SERVICE_PATH = "/development/exceptionthrower";

    public RestExceptionThrowerCommand() {
        super(Port.class);
    }

    @Override
    public String call(@NotNull Optional<ExceptionThrowerParams> arg, @NotNull ExecutionContext ec) throws Exception {
        return getPort(ec).call(getApiKey(ec), arg.get().getExceptionType().name(), arg.get().getExceptionChance());
    }

    private String getApiKey(ExecutionContext ec) {
        return ec.getMode().get(DemoappKeys.API_KEY).get();
    }

    @NotNull @Override
    protected Callable<Port> createPort(@NotNull final ExecutionContext ec) {
        return new Callable<Port>() {
            @Override
            public Port call() throws Exception {
                return new Port(makeClient(ec), SERVICE_PATH);
            }
        };
    }
}
