package com.optimaize.anythingworks.exampleproject.clientlib.rest.services.development.post;

import com.google.common.base.Optional;
import com.optimaize.anythingworks.client.rest.RestBaseCommand;
import com.optimaize.anythingworks.exampleproject.clientlib.DemoappKeys;
import com.optimaize.anythingworks.exampleproject.ontology.rest.development.post.ComplexObject;
import com.optimaize.command4j.ExecutionContext;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Callable;

/**
 * The post command sends data using the POST payload as JSON.
 *
 * @author Fabian Kessler
 */
public class RestPostCommand extends RestBaseCommand<Port, ComplexObject, ComplexObject> {

    private static final String SERVICE_PATH = "/development/post";

    public RestPostCommand() {
        super(Port.class);
    }

    @Override
    public ComplexObject call(@NotNull Optional<ComplexObject> arg, @NotNull ExecutionContext ec) throws Exception {
        return getPort(ec).post(getApiKey(ec), arg.get());
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
