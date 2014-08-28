package com.optimaize.soapworks.server.impljdk;

import com.optimaize.soapworks.server.implcommon.AbstractWebServerHandler;
import com.optimaize.soapworks.server.SoapConfiguredServicesProvider;
import com.optimaize.soapworks.server.SoapServerConfiguration;
import com.optimaize.soapworks.server.SoapWebServerHandler;
import com.optimaize.soapworks.server.SoapWebService;
import org.slf4j.Logger; import org.slf4j.LoggerFactory;
import org.jetbrains.annotations.NotNull;
import javax.xml.ws.Endpoint;
import java.io.IOException;import java.lang.Override;import java.lang.String;
import java.util.List;


/**
 * Handles soap service registration in the JDK built-in http server.
 */
public class SoapJdkWebServerHandler extends AbstractWebServerHandler implements SoapWebServerHandler {

    private final Logger logger = LoggerFactory.getLogger(SoapJdkWebServerHandler.class);

    /**
     * @param serviceProviders all services of it will be published
     */
    public SoapJdkWebServerHandler(@NotNull SoapServerConfiguration soapServerConfiguration,
                                   @NotNull List<SoapConfiguredServicesProvider> serviceProviders) {
        super(soapServerConfiguration, serviceProviders);
    }


    @Override
    public void startServer() throws IOException {
        registerServices();
    }

    private void registerServices() {
        String baseAddress = "http://"+soapServerConfiguration.getHost()+":"+soapServerConfiguration.getPort();
        for (SoapConfiguredServicesProvider serviceProvider : serviceProviders) {
            for (SoapWebService soapWebService : serviceProvider.getAll()) {
                String address = baseAddress + serviceProvider.getPathPrefix() + soapWebService.getServicePath();
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
    }

}
