package com.optimaize.anythingworks.client.rest.http;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Converts to the JAX-RS exceptions with {@link WebApplicationException} and subclasses.
 */
@Deprecated
public class JaxrsWebApplicationExceptionTranslator implements RestWebApplicationExceptionTranslator {

    private static final JaxrsWebApplicationExceptionTranslator INSTANCE = new JaxrsWebApplicationExceptionTranslator();

    public static JaxrsWebApplicationExceptionTranslator getInstance() {
        return INSTANCE;
    }

    private JaxrsWebApplicationExceptionTranslator() {
    }


    @NotNull
    @Override
    public WebApplicationException translate(@NotNull Response.Status status, @Nullable String message) {
        if (message==null) {
            message = status.getReasonPhrase();
        }

        //source, but modified:
        //http://stackoverflow.com/questions/22561527/handling-custom-error-response-in-jax-rs-2-0-client-library

        WebApplicationException webAppException;
        switch (status) {
            case BAD_REQUEST:
                webAppException = new BadRequestException(message);
                break;
            case UNAUTHORIZED:
                webAppException = new NotAuthorizedException(message);
                break;
            case FORBIDDEN:
                webAppException = new ForbiddenException(message);
                break;
            case NOT_FOUND:
                webAppException = new NotFoundException(message);
                break;
            case METHOD_NOT_ALLOWED:
                webAppException = new NotAllowedException(message);
                break;
            case NOT_ACCEPTABLE:
                webAppException = new NotAcceptableException(message);
                break;
            case UNSUPPORTED_MEDIA_TYPE:
                webAppException = new NotSupportedException(message);
                break;

            case INTERNAL_SERVER_ERROR:
                webAppException = new InternalServerErrorException(message);
                break;
            case SERVICE_UNAVAILABLE:
                webAppException = new ServiceUnavailableException(message);
                break;

            default:
                if (status.getFamily() == Response.Status.Family.CLIENT_ERROR) {
                    webAppException = new ClientErrorException(message, status.getStatusCode());
                } else if (status.getFamily() == Response.Status.Family.SERVER_ERROR) {
                    webAppException = new ServerErrorException(message, status.getStatusCode());
                } else {
                    //3xx codes (dunno what to throw)
                    webAppException = new WebApplicationException(message);
                }
        }

        return webAppException;
    }
}
