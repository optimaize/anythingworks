package com.optimaize.anythingworks.server.implgrizzly.rest;

import com.optimaize.anythingworks.common.fault.faultinfo.FaultInfo;
import com.optimaize.anythingworks.common.rest.fault.RestFaultInfo;
import com.optimaize.anythingworks.server.common.fault.FaultInfos;
import com.optimaize.anythingworks.server.rest.FaultInfoEnvelope;
import com.optimaize.anythingworks.server.rest.fault.RestFaultInfoBuilder;
import org.glassfish.jersey.server.ContainerRequest;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * Maps {@link Exception}s to {@link Response}s.
 *
 * This is registered when starting the web server.
 *
 * In the normal case the Exception we get here already is a {@link WebApplicationException}.
 * Anything else would be an exception (ha ha). And yes the interface defines Exception, not Throwable,
 * therefore an Error would not get in here. I don't see how {@link Error}s are handled, where they get
 * mapped, there is no such interface.
 * Because our code uses an exception barrier
 */
public class FaultInfoRestExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<Exception> {

    @Inject
    private javax.inject.Provider<ContainerRequest> containerRequestProvider;


    @Override
    public Response toResponse(Exception e) {
        final RestFaultInfo restFaultInfo;
        if (e instanceof com.optimaize.anythingworks.common.fault.exceptions.ServiceException) {
            FaultInfo faultInfo = ((com.optimaize.anythingworks.common.fault.exceptions.ServiceException) e).getFaultInfo();
            if (!(faultInfo instanceof RestFaultInfo)) {
                //omg! TODO this is ugly. can't use generics with exceptions.
                restFaultInfo = RestFaultInfoBuilder.faultInfo(
                        FaultInfos.Server.InternalServerError.internalServerError("Expected RestFaultInfo but got FaultInfo for error: " + faultInfo.getMessage()),
                        Response.Status.INTERNAL_SERVER_ERROR
                );
            } else {
                restFaultInfo = (RestFaultInfo)faultInfo;
            }
        } else {
            //really shouldn't get here... there is an exception barrier earlier already.
            //if we get here then either some web service did not go through the usual barrier,
            //or another piece of software threw another exception. that's possible also.
            restFaultInfo = RestFaultInfoBuilder.faultInfo(
                    FaultInfos.Server.InternalServerError.internalServerError("Unexpected late exception translation, caused by: " + e.getMessage()),
                    Response.Status.INTERNAL_SERVER_ERROR
            );
        }

        Object entity;
        if (useEnvelope()) {
            entity = wrapEnvelope(restFaultInfo);
        } else {
            entity = restFaultInfo;
        }

        return Response
                .status(new Response.StatusType() {
                    @Override
                    public int getStatusCode() {
                        return restFaultInfo.getHttpStatusCode();
                    }
                    @Override
                    public Response.Status.Family getFamily() {
                        return Response.Status.Family.familyOf(restFaultInfo.getHttpStatusCode());
                    }
                    @Override
                    public String getReasonPhrase() {
                        return restFaultInfo.getHttpStatusMeaning();
                    }
                })
                .entity(entity)
                .type("application/json")
                .build();
    }


    private Object wrapEnvelope(RestFaultInfo faultInfo) {
        return FaultInfoEnvelope.error(faultInfo);
    }

    private boolean useEnvelope() {
        try {
            String envelope = containerRequestProvider.get().getUriInfo().getQueryParameters().getFirst("envelope");
            if (envelope!=null && envelope.equalsIgnoreCase("true")) {
                //i have tested the boolean interpretation from jax-rs or jackson, whoever takes care of it, and
                //that's exactly how it behaves: "true" ignoring case is true, everything else (including "on", "1"
                //and so on) is false.
                //i'm not judging, i just do it exactly the same way, so that exceptions are consistently returned
                //in line with success results.
                return true;
            }
        } catch (Exception e) {
            //never mind.
        }
        return false;
    }

}
