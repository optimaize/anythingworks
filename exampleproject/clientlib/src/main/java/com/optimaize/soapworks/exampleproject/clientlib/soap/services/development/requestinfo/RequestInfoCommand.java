package com.optimaize.soapworks.exampleproject.clientlib.soap.services.development.requestinfo;

import com.google.common.base.Optional;
import com.optimaize.command4j.ExecutionContext;
import com.optimaize.soapworks.client.soap.SoapBaseCommand;
import com.optimaize.soapworks.exampleproject.clientlib.DemoappKeys;
import com.optimaize.soapworks.exampleproject.clientlib.soap.services.development.requestinfo.wsdl.SoapRequestInfoService;
import com.optimaize.soapworks.exampleproject.clientlib.soap.services.development.requestinfo.wsdl.SoapRequestInfoServiceService;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.concurrent.Callable;

/**
 * The requestinfo command returns info about the request.
 * This is to debug and demonstrate how to get http request data from within a service method.
 *
 * @author Fabian Kessler
 */
public class RequestInfoCommand extends SoapBaseCommand<SoapRequestInfoService, Void, String> {

    private static final String servicePath = "/system/requestinfo";

    public RequestInfoCommand() {
        super(SoapRequestInfoService.class);
    }

    @Override
    public String call(@NotNull Optional<Void> arg, @NotNull ExecutionContext ec) throws Exception {
        return getPort(ec).requestInfo(getApiKey(ec));
    }

    private String getApiKey(ExecutionContext ec) {
        return ec.getMode().get(DemoappKeys.API_KEY).get();
    }

    @NotNull @Override
    protected Callable<SoapRequestInfoService> createPort(@NotNull final ExecutionContext ec) {
        return new Callable<SoapRequestInfoService>() {
            @Override
            public SoapRequestInfoService call() throws Exception {
                URL url = makeUrl(ec, servicePath);
                return new SoapRequestInfoServiceService(url).getSoapRequestInfoServicePort();
            }
        };
    }
}
