package com.optimaize.soapworks.server.implgrizzly;

import com.optimaize.soapworks.server.implcommon.AbstractWebServerHandler;
import com.optimaize.soapworks.server.SoapConfiguredServicesProvider;
import com.optimaize.soapworks.server.SoapServerConfiguration;
import com.optimaize.soapworks.server.SoapWebServerHandler;
import com.optimaize.soapworks.server.SoapWebService;
import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.NetworkListener;
import org.glassfish.grizzly.jaxws.JaxwsHandler;
import org.glassfish.grizzly.threadpool.ThreadPoolConfig;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.BindException;
import java.util.List;


/**
 * Handles soap service registration in the Grizzly http server.
 *
 * @deprecated this was refactored into GrizzlyHttpServer and GrizzlySoapWebServicePublisher.
 */
@Deprecated
public class SoapGrizzlyWebServerHandler extends AbstractWebServerHandler implements SoapWebServerHandler {

    //private final Logger logger = LoggerFactory.getLogger(SoapGrizzlyWebServerHandler.class);
    private final Logger loggerOnoserver = LoggerFactory.getLogger("onoserver.general");

    public SoapGrizzlyWebServerHandler(@NotNull SoapServerConfiguration soapServerConfiguration,
                                       @NotNull List<SoapConfiguredServicesProvider> serviceProviders) {
        super(soapServerConfiguration, serviceProviders);
    }


    @Override
    public void startServer() throws IOException {
        //TODO the creation and starting of the http server should NOT be done here, not even in this class at all.
        //is should be outside so that it can be reused for other things, such as REST and regular web pages.
        final HttpServer httpServer = new HttpServer();
        registerServicesInGrizzly(httpServer);
        try {
            httpServer.start();
        } catch (BindException e) {
            if (e.getMessage().startsWith("Cannot assign requested address")) {
                //java.net.BindException: "Cannot assign requested address: bind"
                //This catch and rethrow is just an improvement for the human.
                //The error occurs when the machine is not listening on the host name.
                //On a development machine one may try to bind to api.example.com while the machine
                //does not have that host name assigned. Won't work. The host name can be added to the hosts file.
                //Also, the exception is stupid because it does not say WHICH ADDRESS. And it does not let us
                //wrap the original ex as the cause. Ugh. Still I want to keep throwing BindException and not
                //something else. (Could use a subclass, but nah.)
                throw new BindException("Cannot listen on address: '"+soapServerConfiguration.getHost()+"'. This usually means that the machine is not listening on that address. If it's a development machine: add a hosts entry to ip 127.0.0.1.");
            }
            throw e; //rethrow as it was.
        } finally {
            Runtime.getRuntime().addShutdownHook(new Thread(new java.lang.Runnable() {
                @Override
                public void run() {
                    if (httpServer.isStarted()) {
                        try {
                            httpServer.stop();
                        } catch (Throwable e) {
                            //never mind.
                            loggerOnoserver.warn("Failed stopping http server.", e);
                        }
                    }
                }
            }));
        }
    }

    private void registerServicesInGrizzly(HttpServer httpServer) {
        NetworkListener networkListener = new NetworkListener("jaxws-listener", soapServerConfiguration.getHost(), soapServerConfiguration.getPort());
        configureTransportAndThreading(networkListener);
        for (SoapConfiguredServicesProvider serviceProvider : serviceProviders) {
            for (SoapWebService soapWebService : serviceProvider.getAll()) {
                String address = serviceProvider.getPathPrefix() + soapWebService.getServicePath();
                loggerOnoserver.info("Publishing soap web service: "+makeFullUrl(soapServerConfiguration, address));
                HttpHandler jaxwsHandler = new JaxwsHandler(soapWebService);
                httpServer.getServerConfiguration().addHttpHandler(jaxwsHandler, address);
            }
        }
        httpServer.addListener(networkListener);
    }

    private String makeFullUrl(@NotNull SoapServerConfiguration soapServerConfiguration, @NotNull String address) {
        return "http://"+soapServerConfiguration.getHost() + ":"+soapServerConfiguration.getPort() + address;
    }

    private void configureTransportAndThreading(@NotNull NetworkListener networkListener) {
        //WorkerThreadIOStrategy is the default anyway.
        //networkListener.getTransport().setIOStrategy(WorkerThreadIOStrategy.getInstance());

        ThreadPoolConfig config = ThreadPoolConfig.defaultConfig(); //makes a copy
        Integer maxThreadPoolSize = soapServerConfiguration.getMaxThreadPoolSize();
        if (maxThreadPoolSize == null) {
            //auto-compute.
            int num = Runtime.getRuntime().availableProcessors() * 2;
            int corePoolSize = config.getCorePoolSize();
            if (num < corePoolSize) num = corePoolSize;
            config.setMaxPoolSize(num);
        } else {
            int num = maxThreadPoolSize;
            int corePoolSize = config.getCorePoolSize();
            if (num < corePoolSize) {
                num = corePoolSize;
            }
            config.setMaxPoolSize(num);
        }

        config.setQueueLimit( soapServerConfiguration.getThreadPoolQueueLimit() );

        //config.getQueue().size(); //69364  69357
        //config.setKeepAliveTime(5555, TimeUnit.MILLISECONDS);

        networkListener.getTransport().setWorkerThreadPoolConfig(config);
        //networkListener.getTransport().get
    }

}
