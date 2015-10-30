package com.optimaize.soapworks.exampleproject.server.services.rest.system;

import com.google.common.collect.ImmutableList;
import com.optimaize.soapworks.exampleproject.server.services.rest.system.ping.RestPingService;
import com.optimaize.soapworks.server.rest.RestWebService;
import com.optimaize.soapworks.server.rest.RestWebServiceProvider;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 */
@Service
public class RestSystemWebServiceProvider implements RestWebServiceProvider {

    @Inject
    private RestPingService ping;

    @NotNull
    public List<RestWebService> getAll() {
        return ImmutableList.<RestWebService>of(
                ping
        );
    }
}
