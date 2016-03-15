package com.optimaize.anythingworks.exampleproject.server.services.soap.system;

import com.google.common.collect.ImmutableList;
import com.optimaize.anythingworks.exampleproject.server.services.soap.V1SoapServiceProvider;
import com.optimaize.anythingworks.exampleproject.server.services.soap.system.ping.SoapPingService;
import com.optimaize.anythingworks.server.soap.SoapWebService;
import com.optimaize.anythingworks.server.soap.SoapWebServiceProvider;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 */
@Service
public class V1SystemSoapWebServiceProvider implements SoapWebServiceProvider, V1SoapServiceProvider {

    @Inject
    private SoapPingService ping;

    @NotNull
    public List<SoapWebService> getAll() {
        return ImmutableList.<SoapWebService>of(
                ping
        );
    }
}
