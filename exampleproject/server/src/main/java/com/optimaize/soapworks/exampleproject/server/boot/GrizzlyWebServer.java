package com.optimaize.soapworks.exampleproject.server.boot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.optimaize.soapworks.common.host.Host;
import com.optimaize.soapworks.server.implcommon.rest.ServerJacksonJsonMarshallerFactory;
import com.optimaize.soapworks.server.implgrizzly.GrizzlyHttpServer;
import com.optimaize.soapworks.server.implgrizzly.GrizzlySoapWebServicePublisher;
import com.optimaize.soapworks.server.implgrizzly.rest.RestExceptionMapper;
import com.optimaize.soapworks.server.rest.RestWebService;
import com.optimaize.soapworks.server.rest.RestWebServiceProvider;
import com.optimaize.soapworks.server.soap.SoapWebServiceProvider;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.NetworkListener;
import org.glassfish.grizzly.threadpool.ThreadPoolConfig;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.IOException;
import java.net.URI;
import java.util.List;

/**
 * This example uses Grizzly.
 */
@Service
public class GrizzlyWebServer implements WebServer {

    private static final Logger log = LoggerFactory.getLogger(GrizzlyWebServer.class);

    @Inject
    private List<SoapWebServiceProvider> soapWebServiceProviders;
    @Inject
    private List<RestWebServiceProvider> restWebServiceProviders;

    @Override
    public void start() throws IOException {

        //this setup code for Grizzly with optional REST is ugly.
        //see http://stackoverflow.com/questions/33320872/how-do-i-register-jax-rs-into-an-existing-grizzly-httpserver
        //i'm hoping to get an answer about how to do it nicer.
        GrizzlyHttpServer grizzlyHttpServer;

        if (!restWebServiceProviders.isEmpty()) {
            ResourceConfig resourceConfig = new ResourceConfig();

            //for data binding:
            //we customize it a bit with our own preferences.
            JacksonJaxbJsonProvider provider = new JacksonJaxbJsonProvider();
            ObjectMapper objectMapper = ServerJacksonJsonMarshallerFactory.create().getJackson();
            provider.setMapper(objectMapper);
            resourceConfig.register(provider);
            resourceConfig.register(JacksonFeature.class);
            resourceConfig.register(RestExceptionMapper.class);

            //register rest services
            for (RestWebServiceProvider restWebServiceProvider : restWebServiceProviders) {
                for (RestWebService restWebService : restWebServiceProvider.getAll()) {
                    log.info("Publishing rest web services for class: "+restWebService.getClass().getSimpleName());
                    resourceConfig.register(restWebService);
                }
            }
            HttpServer httpServer = GrizzlyHttpServerFactory.createHttpServer(
                    URI.create("http://localhost:80/rest"),
                    resourceConfig,
                    false
            );
            grizzlyHttpServer = new GrizzlyHttpServer(httpServer, new Host("localhost", 80));

        } else {
            grizzlyHttpServer = new GrizzlyHttpServer(new Host("localhost", 80));
        }

        //register soap services
        GrizzlySoapWebServicePublisher soapPublisher = grizzlyHttpServer.getSoapWebServicePublisher("/soap/v1/");
        configureGrizzly(soapPublisher.getNetworkListener()); //TODO this is ugly, should be for all, not in soap area.
        soapPublisher.publishServicesByProviders(soapWebServiceProviders);

        grizzlyHttpServer.start();
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
