package com.optimaize.soapworks.exampleproject.server.boot;

import com.optimaize.soapworks.SoapWebServiceProvider;
import com.optimaize.soapworks.SoapWebServicePublisher;
import com.optimaize.soapworks.common.host.Host;
import com.optimaize.soapworks.implgrizzly.GrizzlyHttpServer;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.IOException;
import java.util.List;

/**
 */
@Service
public class SoapWebServer {

    @Inject
    private List<SoapWebServiceProvider> webServiceProviders;

    public void start() throws IOException {
        GrizzlyHttpServer httpServer = new GrizzlyHttpServer(new Host("localhost", 80));
        SoapWebServicePublisher soapPublisher = httpServer.getSoapWebServicePublisher("/soap/v1/");
        soapPublisher.publishServicesByProviders(webServiceProviders);
        httpServer.start();
    }

}
