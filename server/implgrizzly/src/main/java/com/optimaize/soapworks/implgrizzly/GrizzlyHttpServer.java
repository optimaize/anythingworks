package com.optimaize.soapworks.implgrizzly;

import com.optimaize.soapworks.SoapWebServicePublisher;
import com.optimaize.soapworks.common.host.Host;
import com.optimaize.soapworks.implcommon.TransportInfo;
import org.glassfish.grizzly.http.server.HttpServer;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 *
 */
public class GrizzlyHttpServer {

    private final HttpServer httpServer;

    private final Host host;

    private Map<TransportInfo, SoapWebServicePublisher> soapPublishers = new HashMap<>();

    public GrizzlyHttpServer(Host host) {
        this(new HttpServer(), host);
    }
    public GrizzlyHttpServer(HttpServer httpServer, Host host) {
        this.httpServer = httpServer;
        this.host = host;
    }

    public SoapWebServicePublisher getSoapWebServicePublisher(@NotNull String pathPrefix) {
        return getSoapWebServicePublisher(new TransportInfo(host, pathPrefix));
    }
    public synchronized SoapWebServicePublisher getSoapWebServicePublisher(@NotNull TransportInfo transportInfo) {
        SoapWebServicePublisher publisher = soapPublishers.get(transportInfo);
        if (publisher==null) {
            publisher = new GrizzlySoapWebServicePublisher(httpServer, transportInfo);
            soapPublishers.put(transportInfo, publisher);
        }
        return publisher;
    }

    public void start() throws IOException {
        httpServer.start();
        addShutdownHook();
    }

    private void addShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                if (httpServer.isStarted()) {
                    try {
                        httpServer.stop();
                    } catch (Throwable e) {
                        //never mind.
                    }
                }
            }
        }));
    }

}
