package com.optimaize.soapworks.exampleproject.server.boot;

import com.optimaize.soapworks.server.SoapWebServiceProvider;
import com.optimaize.soapworks.common.host.Host;
import com.optimaize.soapworks.server.implgrizzly.GrizzlyHttpServer;
import com.optimaize.soapworks.server.implgrizzly.GrizzlySoapWebServicePublisher;
import org.glassfish.grizzly.http.server.NetworkListener;
import org.glassfish.grizzly.threadpool.ThreadPoolConfig;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.IOException;
import java.util.List;

/**
 * This example uses Grizzly.
 */
@Service
public class GrizzlySoapWebServer implements SoapWebServer {

    @Inject
    private List<SoapWebServiceProvider> webServiceProviders;

    @Override
    public void start() throws IOException {
        GrizzlyHttpServer httpServer = new GrizzlyHttpServer(new Host("localhost", 80));
        GrizzlySoapWebServicePublisher soapPublisher = httpServer.getSoapWebServicePublisher("/soap/v1/");
        configureGrizzly(soapPublisher.getNetworkListener());
        soapPublisher.publishServicesByProviders(webServiceProviders);
        httpServer.start();
    }

    private void configureGrizzly(@NotNull NetworkListener networkListener) {
        configureWorkerThreads(networkListener);
    }

    /**
     * See https://grizzly.java.net/coreconfig.html
     * See https://grizzly.java.net/bestpractices.html
     */
    private void configureWorkerThreads(@NotNull NetworkListener networkListener) {
        //WorkerThreadIOStrategy is the default anyway.
        //networkListener.getTransport().setIOStrategy(WorkerThreadIOStrategy.getInstance());

        ThreadPoolConfig config = ThreadPoolConfig.defaultConfig(); //makes a copy
        int numThreads = Runtime.getRuntime().availableProcessors() * 2;
        //setting core and max to the same number is important!
        config.setCorePoolSize(numThreads);
        config.setMaxPoolSize(numThreads);
        //a queue limit is optional but recommended
        config.setQueueLimit(1000);
        networkListener.getTransport().setWorkerThreadPoolConfig(config);
    }

}
