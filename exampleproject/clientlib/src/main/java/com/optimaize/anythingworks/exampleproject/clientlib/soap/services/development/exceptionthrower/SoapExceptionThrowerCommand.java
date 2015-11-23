package com.optimaize.anythingworks.exampleproject.clientlib.soap.services.development.exceptionthrower;

import com.google.common.base.Optional;
import com.optimaize.anythingworks.client.soap.SoapBaseCommand;
import com.optimaize.anythingworks.exampleproject.clientlib.DemoappKeys;
import com.optimaize.anythingworks.exampleproject.clientlib.soap.services.development.exceptionthrower.wsdl.SoapExceptionThrower;
import com.optimaize.anythingworks.exampleproject.clientlib.soap.services.development.exceptionthrower.wsdl.SoapExceptionThrowerService;
import com.optimaize.command4j.ExecutionContext;
import org.jetbrains.annotations.NotNull;

import java.net.URL;
import java.util.concurrent.Callable;

/**
 * @author Fabian Kessler
 */
public class SoapExceptionThrowerCommand extends SoapBaseCommand<SoapExceptionThrower, ExceptionThrowerParams, String> {

    private static final String servicePath = "/development/exceptionthrower";

    public SoapExceptionThrowerCommand() {
        super(SoapExceptionThrower.class);
    }

    @Override
    public String call(@NotNull Optional<ExceptionThrowerParams> arg, @NotNull ExecutionContext ec) throws Exception {
        return getPort(ec).throwException(getApiKey(ec), arg.get().getExceptionType().name(), arg.get().getExceptionChance());
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
