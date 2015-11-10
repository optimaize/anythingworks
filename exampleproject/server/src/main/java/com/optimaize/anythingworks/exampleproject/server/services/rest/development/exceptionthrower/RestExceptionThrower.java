package com.optimaize.anythingworks.exampleproject.server.services.rest.development.exceptionthrower;

import com.google.common.base.Optional;
import com.optimaize.command4j.Command;
import com.optimaize.command4j.ExecutionContext;
import com.optimaize.command4j.commands.BaseCommand;
import com.optimaize.anythingworks.exampleproject.server.lib.BaseWebService;
import com.optimaize.anythingworks.server.rest.RestWebService;
import io.swagger.annotations.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Random;

/**
 * This service throws exceptions at the user's choice.
 */
@Service
@Path("/v1/development")
@Produces({"application/json"})
@Api(value = "/v1/development", description = "Operations for development.")
public class RestExceptionThrower extends BaseWebService implements RestWebService {

    @GET
    @Path("/exceptionthrower")
    @ApiOperation(value = "Tell the server to throw an exception.",
            notes = "This is useful for developing against server exceptions. What happens on invalid input, and so on. Just tell the server to throw one, and see what happens in your code and how you handle it.",
            response = String.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Returns 'OK' in case the server doesn't throw."),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public Response throwException(
            @QueryParam(value = "apiKey") final String apiKey,
            @QueryParam(value = "envelope") final boolean envelope,
            @ApiParam(value = "Which exception you'd like to receive.", allowableValues = "BadRequest, NotAuthorized, Forbidden, InternalServerError") @QueryParam(value = "exceptionType") final String exceptionType,
            @ApiParam(value = "Should the server always throw, or just sometimes? 0-100 where 100 means 100% chance of throwing.", allowableValues = "range[0,100]") @QueryParam(value = "exceptionChance") final int exceptionChance
    ) {
        String result = execute(new BaseCommand<Void, String>() {
            @Override
            public String call(@NotNull Optional<Void> arg, @NotNull ExecutionContext ec) throws Exception {
                Random r = new Random();
                int randomInt = r.nextInt(101); //returns 0-100 (101 is exclusive)
                if (exceptionChance != 0 && randomInt <= exceptionChance) {
                    if (exceptionType.equals("BadRequest")) {
                        throw new BadRequestException("Your data was not understood.");
                    } else if (exceptionType.equals("NotAuthorized")) {
                        throw new NotAuthorizedException("Unknown api key: " + apiKey);
                    } else if (exceptionType.equals("Forbidden")) {
                        throw new ForbiddenException("This service is not for you.");
                    } else if (exceptionType.equals("InternalServerError")) {
                        throw new InternalServerErrorException("No one knows what happened.");
                    }
                    throw new WebApplicationException("Something unspecified");
                } else {
                    return "OK";
                }
            }
        }).get();

        Object entity = possiblyWrapInEnvelope(envelope, result);
        return Response.ok().entity(entity).build();
    }


    @NotNull
    protected Optional<String> execute(Command<Void, String> command) {
        return restExceptionBarrier(command, modeFactory.restDefaultMode(), null);
    }

}
