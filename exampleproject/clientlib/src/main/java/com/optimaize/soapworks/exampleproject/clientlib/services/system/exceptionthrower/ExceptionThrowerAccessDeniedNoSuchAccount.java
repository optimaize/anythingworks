package com.optimaize.soapworks.exampleproject.clientlib.services.system.exceptionthrower;

import com.google.common.base.Optional;
import com.optimaize.command4j.ExecutionContext;
import com.optimaize.soapworks.client.SoapBaseCommand;
import com.optimaize.soapworks.exampleproject.clientlib.lib.DemoappKeys;
import com.optimaize.soapworks.exampleproject.clientlib.services.system.exceptionthrower.wsdl.SoapExceptionThrower;
import com.optimaize.soapworks.exampleproject.clientlib.services.system.exceptionthrower.wsdl.SoapExceptionThrowerService;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.concurrent.Callable;

/**
 * @author Fabian Kessler
 */
public class ExceptionThrowerAccessDeniedNoSuchAccount extends SoapBaseCommand<SoapExceptionThrower, Void, String> {

    private static final String servicePath = "/system/exceptionthrower";

    public ExceptionThrowerAccessDeniedNoSuchAccount() {
        super(SoapExceptionThrower.class);
    }

    @Override
    public String call(@NotNull Optional<Void> arg, @NotNull ExecutionContext ec) throws Exception {
        return getPort(ec).throwAccessDeniedNoSuchAccount(getApiKey(ec));
    }

    private String getApiKey(ExecutionContext ec) {
        return ec.getMode().get(DemoappKeys.API_KEY).get();
    }

    @NotNull @Override
    protected Callable<SoapExceptionThrower> createPort(@NotNull final ExecutionContext ec) {
        return new Callable<SoapExceptionThrower>() {
            @Override
            public SoapExceptionThrower call() throws Exception {
                URL url = makeUrl(ec, servicePath);
                return new SoapExceptionThrowerService(url).getSoapExceptionThrowerPort();
            }
        };
    }

}
