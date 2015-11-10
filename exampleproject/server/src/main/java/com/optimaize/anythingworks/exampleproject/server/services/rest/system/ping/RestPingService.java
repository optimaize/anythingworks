package com.optimaize.anythingworks.exampleproject.server.services.rest.system.ping;

import com.google.common.base.Optional;
import com.optimaize.command4j.Command;
import com.optimaize.command4j.ExecutionContext;
import com.optimaize.command4j.commands.BaseCommand;
import com.optimaize.anythingworks.exampleproject.server.lib.BaseWebService;
import com.optimaize.anythingworks.server.rest.RestWebService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 *
 */
@Service
@Path("/system")
@Produces({"application/json"})
@Api(value = "/system", description = "Operations for pinging the server.")
public class RestPingService extends BaseWebService implements RestWebService {

    @GET
    @Path("/ping")
    @ApiOperation(value = "Ping the server",
            notes = "Gives you a hint of the raw network time it takes for a round trip to the server and back, through the whole web service stack.",
            response = String.class
    )
        @ApiResponses(value = { @ApiResponse(code = 200, message = "Returns 'pong'")})
    public Response ping(
            @QueryParam(value = "apiKey") final String apiKey,
            @QueryParam(value = "envelope") final boolean envelope
    ) {
        String result = execute(new BaseCommand<Object, String>() {
            @Override
            public String call(@NotNull Optional<Object> arg, @NotNull ExecutionContext ec) throws Exception {
                return "pong";
            }
        }).orNull();

        Object entity = possiblyWrapInEnvelope(envelope, result);
        return Response.ok().entity(entity).build();
    }



    @NotNull
    protected Optional<String> execute(Command<Object, String> command) {
        return restExceptionBarrier(command, modeFactory.restDefaultMode(), null);
    }

}
