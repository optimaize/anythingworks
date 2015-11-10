package com.optimaize.anythingworks.exampleproject.server.services.rest.development.requestinfo;

import com.google.common.base.Optional;
import com.optimaize.command4j.Command;
import com.optimaize.command4j.ExecutionContext;
import com.optimaize.command4j.commands.BaseCommand;
import com.optimaize.anythingworks.exampleproject.server.lib.BaseWebService;
import com.optimaize.anythingworks.server.rest.RestWebService;
import org.glassfish.jersey.server.ContainerRequest;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Returns information about the request (URL and such).
 */
@Service
@Path("/v1/development")
@Produces({"application/json"})
public class RestRequestInfoService extends BaseWebService implements RestWebService {

    @GET
    @Path("/requestinfo")
    public Response info(
            @QueryParam(value = "apiKey") final String apiKey,
            @QueryParam(value = "envelope") final boolean envelope,
            @Context final HttpHeaders httpHeaders,
            @Context final Request request
    ) {
        RequestInfo result = execute(new BaseCommand<Void, RequestInfo>() {
            @Override
            public RequestInfo call(@NotNull Optional<Void> arg, @NotNull ExecutionContext ec) throws Exception {
                RequestInfo.Builder builder = new RequestInfo.Builder();
                builder.method(request.getMethod());
                builder.uri(((ContainerRequest) request).getRequestUri().toString());
                List<String> requestHeader = httpHeaders.getRequestHeader("user-agent");
                if (requestHeader!=null && !requestHeader.isEmpty()) {
                    builder.userAgent(requestHeader.get(0));
                }
                //TODO add more, that's not all yet.
                return builder.build();
            }
        }).orNull();

        Object entity = possiblyWrapInEnvelope(envelope, result);
        return Response.ok().entity(entity).build();
    }

    @NotNull
    protected Optional<RequestInfo> execute(Command<Void, RequestInfo> command) {
        return restExceptionBarrier(command, modeFactory.restDefaultMode(), null);
    }

}
