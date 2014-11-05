package com.optimaize.soapworks.server.impljdk;

import com.optimaize.soapworks.server.SoapWebService;
import com.optimaize.soapworks.server.implcommon.BaseSoapWebServicePublisher;
import com.optimaize.soapworks.server.implcommon.TransportInfo;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.ws.Endpoint;

/**
 * Uses standard JDK functionality to start an http server and offers methods for registering soap web services easily.
 *
 * There is no start() method; services are published instantly.
 */
public class JdkHttpServer extends BaseSoapWebServicePublisher {

    private final Logger logger = LoggerFactory.getLogger(SoapJdkWebServerHandler.class);

    @NotNull
    private final TransportInfo transportInfo;

    /**
     */
    public JdkHttpServer(@NotNull TransportInfo transportInfo) {
        this.transportInfo = transportInfo;
    }


    @Override
    public void publishService(SoapWebService soapWebService) {
        String baseAddress = transportInfo.getHost().toUriString();
        String address = baseAddress + transportInfo.getBasePath() + soapWebService.getServicePath();
        logger.info("Publishing soap web service: "+address);
        Endpoint endpoint = Endpoint.publish(address, soapWebService);

        //            //see http://stackoverflow.com/questions/1945618/tracing-xml-request-responses-with-jax-ws
        //            //and http://stackoverflow.com/questions/2808544/java-jax-ws-web-service-client-how-log-request-response-xml/2808663#2808663
        //            Binding binding = endpoint.getBinding();
        //            List<Handler> handlerChain = binding.getHandlerChain();
        //            handlerChain.add(new SOAPLoggingHandler());
        //            binding.setHandlerChain(handlerChain);
    }
}

