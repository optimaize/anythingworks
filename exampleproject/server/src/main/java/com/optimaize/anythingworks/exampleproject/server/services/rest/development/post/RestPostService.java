package com.optimaize.anythingworks.exampleproject.server.services.rest.development.post;

import com.google.common.base.Optional;
import com.optimaize.anythingworks.exampleproject.ontology.rest.development.post.Circle;
import com.optimaize.anythingworks.exampleproject.ontology.rest.development.post.ComplexObject;
import com.optimaize.anythingworks.exampleproject.server.lib.BaseWebService;
import com.optimaize.anythingworks.server.rest.RestWebService;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * This service uses a complex JSON input that comes in the http post body payload.
 * The result is complex also.
 */
@Service
@Path("/v1/development")
public class RestPostService extends BaseWebService implements RestWebService {

    @GET
    @Path("/get")
    @Produces({"application/json"})
    public Response get(
            @QueryParam(value = "envelope") final boolean envelope
    ) {
        ComplexObject result = new ComplexObject("theresult", 15, true, ComplexObject.Color.RED, new Circle("blue", 5d), Optional.of("foobar"), Optional.<String>absent());

        Object entity = possiblyWrapInEnvelope(envelope, result);
        return Response.ok().entity(entity).build();
    }

    @POST
    @Path("/post")
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public Response post(
            @QueryParam(value = "apiKey") final String apiKey,
            @QueryParam(value = "envelope") final boolean envelope,
            ComplexObject complexParam
    ) {
        ComplexObject result = new ComplexObject(
                complexParam.getString() + "-result",
                complexParam.getNumber() * 2,
                !complexParam.isYesOrNo(),
                complexParam.getColor(),
                new Circle("light" + complexParam.getGeometricalFigure().getColor(), 5d),
                Optional.of(complexParam.getOptional1().get() + "bar"),
                Optional.<String>absent()
        );

        Object entity = possiblyWrapInEnvelope(envelope, result);
        return Response.ok().entity(entity).build();
    }

}
