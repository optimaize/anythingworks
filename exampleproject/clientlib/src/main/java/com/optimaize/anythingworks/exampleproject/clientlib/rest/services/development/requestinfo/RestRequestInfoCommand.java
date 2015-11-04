package com.optimaize.anythingworks.exampleproject.clientlib.rest.services.development.requestinfo;

import com.google.common.base.Optional;
import com.optimaize.anythingworks.client.rest.RestBaseCommand;
import com.optimaize.anythingworks.exampleproject.clientlib.DemoappKeys;
import com.optimaize.command4j.ExecutionContext;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Callable;

/**
 * The requestinfo command retrieves information from the server about the request.
 */
public class RestRequestInfoCommand extends RestBaseCommand<RestRequestInfoPort, Void, RequestInfo> {

    private static final String SERVICE_PATH = "/development/requestinfo";

    public RestRequestInfoCommand() {
        super(RestRequestInfoPort.class);
    }

    @Override
    public RequestInfo call(@NotNull Optional<Void> arg, @NotNull ExecutionContext ec) throws Exception {
        return getPort(ec).call(getApiKey(ec));
    }

    private String getApiKey(ExecutionContext ec) {
        return ec.getMode().get(DemoappKeys.API_KEY).get();
    }

    @NotNull @Override
    protected Callable<RestRequestInfoPort> createPort(@NotNull final ExecutionContext ec) {
        return new Callable<RestRequestInfoPort>() {
            @Override
            public RestRequestInfoPort call() throws Exception {
                return new RestRequestInfoPort(makeClient(ec), SERVICE_PATH);
            }
        };
    }
}
