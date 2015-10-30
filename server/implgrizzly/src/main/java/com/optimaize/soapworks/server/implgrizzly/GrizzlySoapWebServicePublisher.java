package com.optimaize.soapworks.server.implgrizzly;

import com.optimaize.soapworks.server.soap.SoapWebService;
import com.optimaize.soapworks.server.implcommon.soap.BaseSoapWebServicePublisher;
import com.optimaize.soapworks.server.implcommon.TransportInfo;
import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpHandlerRegistration;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.NetworkListener;
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
    private final NetworkListener networkListener;

    @NotNull
    private final TransportInfo transportInfo;

    private volatile boolean isNetworkListenerAdded = false;

    public GrizzlySoapWebServicePublisher(@NotNull HttpServer httpServer, @NotNull TransportInfo transportInfo) {
        this(
                httpServer,
                transportInfo,
                new NetworkListener("jaxws-listener", transportInfo.getHost().getHostName(), transportInfo.getHost().getPortNumber())
        );
    }

    /**
     * Overloaded constructor that gives you the chance to hand in a NetworkListener that is configured already.
     */
    public GrizzlySoapWebServicePublisher(@NotNull HttpServer httpServer,
                                          @NotNull TransportInfo transportInfo,
                                          @NotNull NetworkListener networkListener) {
        this.httpServer = httpServer;
        this.transportInfo = transportInfo;
        this.networkListener = networkListener;
    }

    /**
     * Gives you the chance to configure the listener.
     * Note that it's untested whether this works once the HttpServer has been started, or once you have published services.
     */
    @NotNull
    public NetworkListener getNetworkListener() {
        return networkListener;
    }


    @Override
    public void publishService(SoapWebService soapWebService) {
        String path = transportInfo.getBasePath() + soapWebService.getServicePath();
        log.info("Publishing soap web service: " + transportInfo.toUriString() + soapWebService.getServicePath() + "?wsdl");
        HttpHandler jaxwsHandler = new JaxwsHandler(soapWebService);
        httpServer.getServerConfiguration().addHttpHandler(jaxwsHandler,
                //see https://java.net/projects/grizzly/lists/users/archive/2014-11/message/7 for why this is done.
                HttpHandlerRegistration.bulder()
                        .contextPath(path)
                        .urlPattern("")
                        .build());

        if (!isNetworkListenerAdded) {
            addNetworkListener();
        }
    }

    /**
     * This is done at most once. And only if at least one service gets published.
     */
    private synchronized void addNetworkListener() {
        if (true) return; //because REST already does it.
        if (!isNetworkListenerAdded) {
            httpServer.addListener(networkListener);
            isNetworkListenerAdded = true;
        }
    }

}
