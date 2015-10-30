package com.optimaize.soapworks.server.implgrizzly;

import com.optimaize.soapworks.common.host.Host;
import com.optimaize.soapworks.server.implcommon.TransportInfo;
import org.glassfish.grizzly.http.server.HttpServer;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Wraps a Grizzly HttpServer instance and offers methods for registering soap web services easily.
 */
public class GrizzlyHttpServer {

    private static final Logger log = LoggerFactory.getLogger(GrizzlyHttpServer.class);

    @NotNull
    public final HttpServer httpServer;

    @NotNull
    private final Host host;

    private Map<TransportInfo, GrizzlySoapWebServicePublisher> soapPublishers = new HashMap<>();

    /**
     * Creates a new HttpServer.
     */
    public GrizzlyHttpServer(@NotNull Host host) {
        this(new HttpServer(), host);
    }

    /**
     * Overloaded constructor where an existing HttpServer instance is given.
     * This is useful if you have other services published already, for example regular html pages.
     */
    public GrizzlyHttpServer(@NotNull HttpServer httpServer, @NotNull Host host) {
        this.httpServer = httpServer;
        this.host = host;
    }

    /**
     * Uses the host given in the constructor.
     * @param pathPrefix Starts and ends with a slash, thus the minimal is to pass in "/".
     * @return The publisher where you can add services.
     */
    public GrizzlySoapWebServicePublisher getSoapWebServicePublisher(@NotNull String pathPrefix) {
        return getSoapWebServicePublisher(new TransportInfo(host, pathPrefix));
    }

    /**
     * Overloaded method that gives you full control over the host instead of using the one given in the constructor.
     * This way you can publish to multiple host names, or different port numbers or protocols.
     * @return The publisher where you can add services.
     */
    public synchronized GrizzlySoapWebServicePublisher getSoapWebServicePublisher(@NotNull TransportInfo transportInfo) {
        GrizzlySoapWebServicePublisher publisher = soapPublishers.get(transportInfo);
        if (publisher==null) {
            publisher = new GrizzlySoapWebServicePublisher(httpServer, transportInfo);
            soapPublishers.put(transportInfo, publisher);
        }
        return publisher;
    }

    public void start() throws IOException {
        try {
            httpServer.start();
        } finally {
            addShutdownHook();
        }
    }

    private void addShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                if (httpServer.isStarted()) {
                    try {
                        httpServer.stop();
                    } catch (Throwable e) {
                        //never mind.
                        log.warn("Failed stopping http server.", e);
                    }
                }
            }
        }));
    }

}
