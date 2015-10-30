package com.optimaize.soapworks.server.implgrizzly.rest;

import com.optimaize.soapworks.common.rest.JsonError;
import com.optimaize.soapworks.server.rest.Envelope;
import org.glassfish.jersey.server.ContainerRequest;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * This is registered when starting the web server.
 */
public class RestExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<Exception> {

    @Inject
    private javax.inject.Provider<ContainerRequest> containerRequestProvider;


    @Override
    public Response toResponse(Exception e) {

        //not proud of this code. optional envelope complicates things on top of all.

        boolean envelope = useEnvelope();
        if (envelope) {
            if (e instanceof WebApplicationException) {
                Response response = ((WebApplicationException) e).getResponse();
                Object entity = response.getEntity();
                if (entity == null || !(entity instanceof JsonError)) {
                    entity = new JsonError(response.getStatus(), response.getStatusInfo().getReasonPhrase(), e.getMessage());
                }
                return Response.fromResponse(response).entity(wrapEnvelope((JsonError)entity)).type("application/json").build();
            } else {
                //really shouldn't get here... there is an exception barrier earlier already.
                //if we get here then either some web service did not go through the usual barrier,
                //or another piece of software threw another exception. that's possible also.
                JsonError entity = new JsonError(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Internal error", e.getMessage());
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(wrapEnvelope(entity)).type("application/json").build();
            }
        } else {
            if (e instanceof WebApplicationException) {
                Response response = ((WebApplicationException) e).getResponse();
                Object entity = response.getEntity();
                if (entity != null) {
                    //return as is
                    return response;
                }
                entity = new JsonError(response.getStatus(), response.getStatusInfo().getReasonPhrase(), e.getMessage());
                return Response.fromResponse(response).entity(entity).type("application/json").build();
            } else {
                //really shouldn't get here... there is an exception barrier earlier already.
                //if we get here then either some web service did not go through the usual barrier,
                //or another piece of software threw another exception. that's possible also.
                JsonError entity = new JsonError(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Internal error", e.getMessage());
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(entity).type("application/json").build();
            }
        }
    }

    private Object wrapEnvelope(JsonError jsonError) {
        return Envelope.error(jsonError);
    }

    private boolean useEnvelope() {
        try {
            String envelope = containerRequestProvider.get().getUriInfo().getQueryParameters().getFirst("envelope");
            if (envelope!=null && envelope.equalsIgnoreCase("true")) {
                return true;
            }
        } catch (Exception e) {
            //never mind.
        }
        return false;
    }

}
