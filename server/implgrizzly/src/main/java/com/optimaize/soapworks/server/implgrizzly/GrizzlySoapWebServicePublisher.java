package com.optimaize.soapworks.server.implgrizzly;

import com.optimaize.soapworks.server.SoapWebService;
import com.optimaize.soapworks.server.SoapWebServiceProvider;
import com.optimaize.soapworks.server.SoapWebServicePublisher;
import com.optimaize.soapworks.server.implcommon.TransportInfo;
import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.NetworkListener;
import org.glassfish.grizzly.jaxws.JaxwsHandler;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;


/**
 * Handles soap service registration in the Grizzly http server.
 */
public class GrizzlySoapWebServicePublisher implements SoapWebServicePublisher {

    private final HttpServer httpServer;
    private final NetworkListener networkListener;

    @NotNull
    private final TransportInfo transportInfo;

    private volatile boolean isNetworkListenerAdded = false;

    public GrizzlySoapWebServicePublisher(HttpServer httpServer, @NotNull TransportInfo transportInfo) {
        this.httpServer = httpServer;
        this.transportInfo = transportInfo;
        networkListener = new NetworkListener("jaxws-listener", transportInfo.getHost().getHostName(), transportInfo.getHost().getPortNumber());
    }


    public void publishServices(SoapWebServiceProvider soapWebServiceProvider) {
        publishServices(soapWebServiceProvider.getAll());

    }

    public void publishServices(Collection<SoapWebService> soapWebService) {
        for (SoapWebService webService : soapWebService) {
            publishService(webService);
        }
    }

    @Override
    public void publishServicesByProviders(Collection<SoapWebServiceProvider> soapWebServiceProviders) {
        for (SoapWebServiceProvider soapWebServiceProvider : soapWebServiceProviders) {
            publishServices(soapWebServiceProvider);
        }
    }

    @Override
    public void publishService(SoapWebService soapWebService) {
        String path = transportInfo.getBasePath() + soapWebService.getServicePath();
        System.out.println("Publishing soap web service: " + transportInfo.toUriString()+soapWebService.getServicePath()+"?wsdl");
        HttpHandler jaxwsHandler = new JaxwsHandler(soapWebService);
        httpServer.getServerConfiguration().addHttpHandler(jaxwsHandler, path);

        if (!isNetworkListenerAdded) {
            addNetworkListener();
        }
    }

    /**
     * This is done at most once. And only if at least one service gets published.
     */
    private synchronized void addNetworkListener() {
        if (!isNetworkListenerAdded) {
            httpServer.addListener(networkListener);
            isNetworkListenerAdded = true;
        }
    }

}
