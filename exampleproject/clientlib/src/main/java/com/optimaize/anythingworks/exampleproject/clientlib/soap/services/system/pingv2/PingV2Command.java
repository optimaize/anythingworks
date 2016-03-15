package com.optimaize.anythingworks.exampleproject.clientlib.soap.services.system.pingv2;

import com.google.common.base.Optional;
import com.optimaize.anythingworks.client.soap.SoapBaseCommand;
import com.optimaize.anythingworks.exampleproject.clientlib.DemoappKeys;
import com.optimaize.anythingworks.exampleproject.clientlib.soap.services.system.pingv2.wsdl.V2SoapPingService;
import com.optimaize.anythingworks.exampleproject.clientlib.soap.services.system.pingv2.wsdl.V2SoapPingServiceService;
import com.optimaize.command4j.ExecutionContext;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.concurrent.Callable;

/**
 * V2 of the ping command.
 */
public class PingV2Command extends SoapBaseCommand<V2SoapPingService, Void, String> {

    private static final String servicePath = "/system/ping";

    public PingV2Command() {
        super(V2SoapPingService.class);
    }

    @Override
    public String call(@NotNull Optional<Void> arg, @NotNull ExecutionContext ec) throws Exception {
        return getPort(ec).ping(getApiKey(ec));
    }

    private String getApiKey(ExecutionContext ec) {
        return ec.getMode().get(DemoappKeys.API_KEY).get();
    }

    @NotNull @Override
    protected Callable<V2SoapPingService> createPort(@NotNull final ExecutionContext ec) {
        return new Callable<V2SoapPingService>() {
            @Override
            public V2SoapPingService call() throws Exception {
                URL url = makeUrl(ec, servicePath);
                return new V2SoapPingServiceService(url).getV2SoapPingServicePort();
            }
        };
    }
}
