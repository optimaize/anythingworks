package com.optimaize.anythingworks.server.implgrizzly;

import com.optimaize.anythingworks.server.implcommon.TransportInfo;
import com.optimaize.anythingworks.server.implcommon.soap.BaseSoapWebServicePublisher;
import com.optimaize.anythingworks.server.soap.SoapWebService;
import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpHandlerRegistration;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.jaxws.JaxwsHandler;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Handles soap service registration in the Grizzly http server.
 */
public class GrizzlySoapWebServicePublisher extends BaseSoapWebServicePublisher {

    private static final Logger log = LoggerFactory.getLogger(GrizzlySoapWebServicePublisher.class);

    @NotNull
    private final HttpServer httpServer;

    @NotNull
    private final TransportInfo transportInfo;


    /**
     */
    public GrizzlySoapWebServicePublisher(@NotNull HttpServer httpServer,
                                          @NotNull TransportInfo transportInfo
    ) {
        this.httpServer = httpServer;
        this.transportInfo = transportInfo;
    }



    @Override
    public void publishService(SoapWebService soapWebService) {
        String path = transportInfo.getBasePath() + soapWebService.getServicePath();
        log.info("Publishing soap web service "+soapWebService.getClass().getName()+" as: " + transportInfo.toUriString() + soapWebService.getServicePath() + "?wsdl");
        HttpHandler jaxwsHandler = new JaxwsHandler(soapWebService, false);
        httpServer.getServerConfiguration().addHttpHandler(jaxwsHandler,
                //see https://java.net/projects/grizzly/lists/users/archive/2014-11/message/7 for why this is done.
                HttpHandlerRegistration.bulder()
                        .contextPath(path)
                        .urlPattern("")
                        .build());
    }

}
