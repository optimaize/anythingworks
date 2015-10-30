package com.optimaize.soapworks.exampleproject.server.services.rest.development.exceptionthrower;

import com.google.common.base.Optional;
import com.optimaize.command4j.Command;
import com.optimaize.command4j.ExecutionContext;
import com.optimaize.command4j.commands.BaseCommand;
import com.optimaize.soapworks.exampleproject.server.lib.BaseWebService;
import com.optimaize.soapworks.server.rest.RestWebService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * This service throws exceptions at the user's choice.
 */
@Service
@Path("/v1/development")
public class RestExceptionThrower extends BaseWebService implements RestWebService {

    @GET
    @Path("/exceptionthrower")
    @Produces({"application/json"})
    public Response throwException(
            @QueryParam(value = "apiKey") final String apiKey,
            @QueryParam(value = "envelope") final boolean envelope,
            @QueryParam(value = "exceptionType") final String exceptionType
    ) {

        execute(new BaseCommand<Void, Void>() {
            @Override
            public Void call(@NotNull Optional<Void> arg, @NotNull ExecutionContext ec) throws Exception {
                if (exceptionType.equals("NotAuthorized")) {
                    throw new NotAuthorizedException("Unknown api key: "+apiKey);
                }
                if (exceptionType.equals("Forbidden")) {
                    throw new NotAuthorizedException("This service is not for you.");
                }
                if (exceptionType.equals("BadRequest")) {
                    throw new BadRequestException("Your data was not understood.");
                }
                if (exceptionType.equals("InternalServerError")) {
                    throw new InternalServerErrorException("No one knows what happened.");
                }
                throw new WebApplicationException("Something unspecified");
            }
        });

        throw new InternalServerErrorException("Unexpected to get here!");
    }


    @NotNull
    protected Optional<Void> execute(Command<Void, Void> command) {
        return restExceptionBarrier(command, modeFactory.restDefaultMode(), null);
    }



}
