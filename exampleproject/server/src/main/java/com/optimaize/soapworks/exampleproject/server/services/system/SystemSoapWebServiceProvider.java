package com.optimaize.soapworks.exampleproject.server.services.system;

import com.google.common.collect.ImmutableList;
import com.optimaize.soapworks.server.SoapWebService;
import com.optimaize.soapworks.server.SoapWebServiceProvider;
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
