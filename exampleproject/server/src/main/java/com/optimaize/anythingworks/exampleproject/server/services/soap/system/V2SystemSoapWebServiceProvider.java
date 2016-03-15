package com.optimaize.anythingworks.exampleproject.server.services.soap.system;

import com.google.common.collect.ImmutableList;
import com.optimaize.anythingworks.exampleproject.server.services.soap.V2SoapServiceProvider;
import com.optimaize.anythingworks.exampleproject.server.services.soap.system.ping.V2SoapPingService;
import com.optimaize.anythingworks.server.soap.SoapWebService;
import com.optimaize.anythingworks.server.soap.SoapWebServiceProvider;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 */
@Service
public class V2SystemSoapWebServiceProvider implements SoapWebServiceProvider, V2SoapServiceProvider {

    @Inject
    private V2SoapPingService ping;

    @NotNull
    public List<SoapWebService> getAll() {
        return ImmutableList.<SoapWebService>of(
                ping
        );
    }
}
