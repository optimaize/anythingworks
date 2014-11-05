package com.optimaize.soapworks.server.implcommon;

import com.optimaize.soapworks.server.SoapConfiguredServicesProvider;
import com.optimaize.soapworks.server.SoapServerConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 */
@Deprecated
public abstract class AbstractWebServerHandler {

    @NotNull
    protected final SoapServerConfiguration soapServerConfiguration;
    @NotNull
    protected final List<SoapConfiguredServicesProvider> serviceProviders;

    public AbstractWebServerHandler(
            @NotNull SoapServerConfiguration soapServerConfiguration,
            @NotNull List<SoapConfiguredServicesProvider> serviceProviders
    ) {
        this.soapServerConfiguration = soapServerConfiguration;
        this.serviceProviders = serviceProviders;
    }

}
