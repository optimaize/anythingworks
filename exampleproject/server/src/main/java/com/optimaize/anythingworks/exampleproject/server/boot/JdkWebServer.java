package com.optimaize.anythingworks.exampleproject.server.boot;

import com.optimaize.anythingworks.common.host.Host;
import com.optimaize.anythingworks.server.soap.SoapWebServiceProvider;
import com.optimaize.anythingworks.server.implcommon.TransportInfo;
import com.optimaize.anythingworks.server.impljdk.JdkHttpServer;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * This example uses the JDK functionality.
 */
@Service
public class JdkWebServer implements WebServer {

    @Inject
    private List<SoapWebServiceProvider> webServiceProviders;

    @Override
    public void start() {
        JdkHttpServer httpServer = new JdkHttpServer(new TransportInfo(new Host("localhost", 80), "/soap/v1/"));
        httpServer.publishServicesByProviders(webServiceProviders);
    }

}
