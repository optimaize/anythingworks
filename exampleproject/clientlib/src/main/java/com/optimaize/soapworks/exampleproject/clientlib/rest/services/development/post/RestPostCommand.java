package com.optimaize.soapworks.exampleproject.clientlib.rest.services.development.post;

import com.google.common.base.Optional;
import com.optimaize.command4j.ExecutionContext;
import com.optimaize.soapworks.client.rest.RestBaseCommand;
import com.optimaize.soapworks.client.rest.http.RestHttpClient;
import com.optimaize.soapworks.exampleproject.clientlib.DemoappKeys;
import com.optimaize.soapworks.client.rest.http.RestHttpClientImpl;
import com.optimaize.soapworks.exampleproject.ontology.rest.development.post.ComplexObject;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.concurrent.Callable;

/**
 * The post command sends data using the POST payload as JSON.
 *
 * @author Fabian Kessler
 */
public class RestPostCommand extends RestBaseCommand<RestPostServicePort, ComplexObject, ComplexObject> {

    private static final String servicePath = "/development/post";

    public RestPostCommand() {
        super(RestPostServicePort.class);
    }

    @Override
    public ComplexObject call(@NotNull Optional<ComplexObject> arg, @NotNull ExecutionContext ec) throws Exception {
        return getPort(ec).post(getApiKey(ec), arg.get());
    }

    private String getApiKey(ExecutionContext ec) {
        return ec.getMode().get(DemoappKeys.API_KEY).get();
    }

    @NotNull @Override
    protected Callable<RestPostServicePort> createPort(@NotNull final ExecutionContext ec) {
        return new Callable<RestPostServicePort>() {
            @Override
            public RestPostServicePort call() throws Exception {
                URL baseUrl = makeBaseUrl(ec);

                RestHttpClient restApiClient = new RestHttpClientImpl.Builder()
                        .basePath(baseUrl.toExternalForm())
                        .userAgent("Java-Client")
                        .build();

                return new RestPostServicePort(restApiClient, servicePath);
            }
        };
    }
}
