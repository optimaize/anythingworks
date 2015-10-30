package com.optimaize.soapworks.exampleproject.server.services.soap.system;

import com.google.common.collect.ImmutableList;
import com.optimaize.soapworks.exampleproject.server.services.soap.development.exceptionthrower.SoapExceptionThrower;
import com.optimaize.soapworks.exampleproject.server.services.soap.development.requestinfo.SoapRequestInfoService;
import com.optimaize.soapworks.exampleproject.server.services.soap.system.ping.SoapPingService;
import com.optimaize.soapworks.server.soap.SoapWebService;
import com.optimaize.soapworks.server.soap.SoapWebServiceProvider;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 */
@Service
public class SystemSoapWebServiceProvider implements SoapWebServiceProvider {

    @Inject
    private SoapPingService ping;
    @Inject
    private SoapRequestInfoService requestInfo;
    @Inject
    private SoapExceptionThrower exceptionThrower;

    @NotNull
    public List<SoapWebService> getAll() {
        return ImmutableList.<SoapWebService>of(
                ping,
                requestInfo,
                exceptionThrower
        );
    }
}
