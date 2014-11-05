package com.optimaize.soapworks.exampleproject.server.boot;

import com.optimaize.soapworks.common.host.Host;
import com.optimaize.soapworks.server.SoapWebServiceProvider;
import com.optimaize.soapworks.server.implcommon.TransportInfo;
import com.optimaize.soapworks.server.impljdk.JdkHttpServer;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * This example uses the JDK functionality.
 */
@Service
public class JdkSoapWebServer implements SoapWebServer {

    @Inject
    private List<SoapWebServiceProvider> webServiceProviders;

    @Override
    public void start() {
        JdkHttpServer httpServer = new JdkHttpServer(new TransportInfo(new Host("localhost", 80), "/soap/v1/"));
        httpServer.publishServicesByProviders(webServiceProviders);
    }

}
