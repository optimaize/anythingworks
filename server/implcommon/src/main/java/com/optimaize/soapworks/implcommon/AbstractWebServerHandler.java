package com.optimaize.soapworks.implcommon;

import com.optimaize.soapworks.SoapConfiguredServicesProvider;
import com.optimaize.soapworks.SoapServerConfiguration;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 */
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
