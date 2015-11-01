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
 * This command tells the server to throw certain exceptions.
 * It can also be at random, meaning it succeeds sometimes and fails other times.
 */
public class RestExceptionThrowerCommand extends RestBaseCommand<RestExceptionThrowerPort, ExceptionThrowerParams, String> {

    private static final String servicePath = "/development/exceptionthrower";

    public RestExceptionThrowerCommand() {
        super(RestExceptionThrowerPort.class);
    }

    @Override
    public String call(@NotNull Optional<ExceptionThrowerParams> arg, @NotNull ExecutionContext ec) throws Exception {
        return getPort(ec).call(getApiKey(ec), arg.get().getExceptionType().name(), arg.get().getExceptionChance());
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
