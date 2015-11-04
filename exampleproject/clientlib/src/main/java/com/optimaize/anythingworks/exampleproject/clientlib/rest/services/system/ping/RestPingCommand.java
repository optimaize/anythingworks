package com.optimaize.anythingworks.exampleproject.clientlib.rest.services.system.ping;

import com.google.common.base.Optional;
import com.optimaize.anythingworks.client.rest.RestBaseCommand;
import com.optimaize.anythingworks.exampleproject.clientlib.DemoappKeys;
import com.optimaize.command4j.ExecutionContext;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Callable;

/**
 * The ping command is provided without any parameters to users. The internal
 * only parameter {@code API-KEY} is raised to a global parameter given with
 * the mode (thus applying to all nested commands and wrappers).
 *
 * @author Fabian Kessler
 */
public class RestPingCommand extends RestBaseCommand<RestPingServicePort, Void, String> {

    private static final String SERVICE_PATH = "/system/ping";

    public RestPingCommand() {
        super(RestPingServicePort.class);
    }

    @Override
    public String call(@NotNull Optional<Void> arg, @NotNull ExecutionContext ec) throws Exception {
        return getPort(ec).ping(getApiKey(ec));
    }

    private String getApiKey(ExecutionContext ec) {
        return ec.getMode().get(DemoappKeys.API_KEY).get();
    }

    @NotNull @Override
    protected Callable<RestPingServicePort> createPort(@NotNull final ExecutionContext ec) {
        return new Callable<RestPingServicePort>() {
            @Override
            public RestPingServicePort call() throws Exception {
                return new RestPingServicePort(makeClient(ec), SERVICE_PATH);
            }
        };
    }
}
