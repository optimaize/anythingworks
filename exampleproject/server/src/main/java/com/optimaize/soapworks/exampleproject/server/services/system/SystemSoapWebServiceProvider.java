package com.optimaize.soapworks.exampleproject.server.services.system;

import com.optimaize.soapworks.server.SoapWebService;
import com.optimaize.soapworks.server.SoapWebServiceProvider;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

/**
 */
@Service
public class SystemSoapWebServiceProvider implements SoapWebServiceProvider {

    @Inject
    private SoapPingService ping;

    @NotNull
    public List<SoapWebService> getAll() {
        return Arrays.<SoapWebService>asList(
                ping
        );
    }
}
