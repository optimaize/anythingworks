package com.optimaize.soapworks.exampleproject.clientlib.soap.services.system.ping;

import com.google.common.base.Optional;
import com.optimaize.command4j.ExecutionContext;
import com.optimaize.soapworks.client.soap.SoapBaseCommand;
import com.optimaize.soapworks.exampleproject.clientlib.DemoappKeys;
import com.optimaize.soapworks.exampleproject.clientlib.soap.services.system.ping.wsdl.SoapPingService;
import com.optimaize.soapworks.exampleproject.clientlib.soap.services.system.ping.wsdl.SoapPingServiceService;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.concurrent.Callable;

/**
 * The ping command is provided without any parameters to users. The internal
 * only parameter {@code API-KEY} is raised to a global parameter given with
 * the mode (thus applying to all nested commands and wrappers).
 *
 * @author Eike Kettner
 */
public class PingCommand extends SoapBaseCommand<SoapPingService, Void, String> {

    private static final String servicePath = "/system/ping";

    public PingCommand() {
        super(SoapPingService.class);
    }

    @Override
    public String call(@NotNull Optional<Void> arg, @NotNull ExecutionContext ec) throws Exception {
        return getPort(ec).ping(getApiKey(ec));
    }

    private String getApiKey(ExecutionContext ec) {
        return ec.getMode().get(DemoappKeys.API_KEY).get();
    }

    @NotNull @Override
    protected Callable<SoapPingService> createPort(@NotNull final ExecutionContext ec) {
        return new Callable<SoapPingService>() {
            @Override
            public SoapPingService call() throws Exception {
                URL url = makeUrl(ec, servicePath);
                return new SoapPingServiceService(url).getSoapPingServicePort();
            }
        };
    }
}
