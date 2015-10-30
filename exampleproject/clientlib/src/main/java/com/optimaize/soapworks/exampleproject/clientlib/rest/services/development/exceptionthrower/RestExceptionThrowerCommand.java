package com.optimaize.soapworks.exampleproject.clientlib.rest.services.development.exceptionthrower;

import com.google.common.base.Optional;
import com.optimaize.command4j.ExecutionContext;
import com.optimaize.soapworks.client.rest.RestBaseCommand;
import com.optimaize.soapworks.client.rest.http.RestHttpClient;
import com.optimaize.soapworks.exampleproject.clientlib.DemoappKeys;
import com.optimaize.soapworks.client.rest.http.RestHttpClientImpl;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.concurrent.Callable;

/**
 *
 */
public class RestExceptionThrowerCommand extends RestBaseCommand<RestExceptionThrowerPort, String, Void> {

    private static final String servicePath = "/development/exceptionthrower";

    public RestExceptionThrowerCommand() {
        super(RestExceptionThrowerPort.class);
    }

    @Override
    public Void call(@NotNull Optional<String> arg, @NotNull ExecutionContext ec) throws Exception {
        return getPort(ec).call(getApiKey(ec), arg.get());
    }

    private String getApiKey(ExecutionContext ec) {
        return ec.getMode().get(DemoappKeys.API_KEY).get();
    }

    @NotNull @Override
    protected Callable<RestExceptionThrowerPort> createPort(@NotNull final ExecutionContext ec) {
        return new Callable<RestExceptionThrowerPort>() {
            @Override
            public RestExceptionThrowerPort call() throws Exception {
                URL baseUrl = makeBaseUrl(ec);

                RestHttpClient restApiClient = new RestHttpClientImpl.Builder()
                        .basePath(baseUrl.toExternalForm())
                        .userAgent("Java-Client")
                        .build();

                return new RestExceptionThrowerPort(restApiClient, servicePath);
            }
        };
    }
}
