package com.optimaize.anythingworks.exampleproject.server.boot;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.optimaize.anythingworks.common.host.Host;
import com.optimaize.anythingworks.exampleproject.server.services.soap.V1SoapServiceProvider;
import com.optimaize.anythingworks.exampleproject.server.services.soap.V2SoapServiceProvider;
import com.optimaize.anythingworks.server.implcommon.rest.ServerJacksonJsonMarshallerFactory;
import com.optimaize.anythingworks.server.implgrizzly.GrizzlyHttpServer;
import com.optimaize.anythingworks.server.implgrizzly.GrizzlySoapWebServicePublisher;
import com.optimaize.anythingworks.server.implgrizzly.rest.CharsetResponseFilter;
import com.optimaize.anythingworks.server.implgrizzly.rest.FaultInfoRestExceptionMapper;
import com.optimaize.anythingworks.server.rest.RestWebService;
import com.optimaize.anythingworks.server.rest.RestWebServiceProvider;
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

    private static final Host HOST = new Host("localhost", 80);


    @Inject
    private List<V1SoapServiceProvider> soapV1WebServiceProviders;
    @Inject
    private List<V2SoapServiceProvider> soapV2WebServiceProviders;
    @Inject
    private List<RestWebServiceProvider> restWebServiceProviders;


    @Override
    public void start() throws IOException {
        //this setup code for Grizzly with optional REST is ugly.
        //see http://stackoverflow.com/questions/33320872/how-do-i-register-jax-rs-into-an-existing-grizzly-httpserver
        //i'm hoping to get an answer about how to do it nicer.
        //also, this code does not support listening on different paths for rest, or different hosts, for example
        //to have services on v1 and v2, or to have services on different ports.
        GrizzlyHttpServer grizzlyHttpServer;

        if (!restWebServiceProviders.isEmpty()) {
            ResourceConfig resourceConfig = makeRestResourceConfig();
            HttpServer httpServer = GrizzlyHttpServerFactory.createHttpServer(
                    URI.create(HOST.toUriString() + "/rest/v1"),
                    resourceConfig,
                    false
            );
            grizzlyHttpServer = new GrizzlyHttpServer(httpServer, HOST);

        } else {
            grizzlyHttpServer = new GrizzlyHttpServer(HOST);
            grizzlyHttpServer.httpServer.addListener(new NetworkListener("my-listener", HOST.getHostName(), HOST.getPortNumber()));
        }

        //register soap services
        grizzlyHttpServer.getSoapWebServicePublisher("/soap/v1/").publishServicesByProviders(soapV1WebServiceProviders);
        grizzlyHttpServer.getSoapWebServicePublisher("/soap/v2/").publishServicesByProviders(soapV2WebServiceProviders);

        for (NetworkListener networkListener : grizzlyHttpServer.httpServer.getListeners()) {
            configureGrizzly(networkListener);
        }

        grizzlyHttpServer.start();
    }

    @NotNull
    private ResourceConfig makeRestResourceConfig() {
        ResourceConfig resourceConfig = new ResourceConfig();

        //for data binding:
        //we customize it a bit with our own preferences.
        JacksonJaxbJsonProvider jacksonProvider = new JacksonJaxbJsonProvider();
        ObjectMapper objectMapper = ServerJacksonJsonMarshallerFactory.create().getJackson();
        jacksonProvider.setMapper(objectMapper);
        resourceConfig.register(jacksonProvider);
        resourceConfig.register(JacksonFeature.class);
//            resourceConfig.register(JsonErrorRestExceptionMapper.class);
        resourceConfig.register(FaultInfoRestExceptionMapper.class);
        resourceConfig.register(CharsetResponseFilter.class);

        //register rest services
        for (RestWebServiceProvider restWebServiceProvider : restWebServiceProviders) {
            for (RestWebService restWebService : restWebServiceProvider.getAll()) {
                log.info("Publishing rest web services for class: "+restWebService.getClass().getName());
                resourceConfig.register(restWebService);
            }
        }
        return resourceConfig;
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
