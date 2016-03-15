package com.optimaize.anythingworks.exampleproject.server.services.soap.development;

import com.google.common.collect.ImmutableList;
import com.optimaize.anythingworks.exampleproject.server.services.soap.development.exceptionthrower.SoapExceptionThrower;
import com.optimaize.anythingworks.exampleproject.server.services.soap.development.requestinfo.SoapRequestInfoService;
import com.optimaize.anythingworks.server.soap.SoapWebService;
import com.optimaize.anythingworks.server.soap.SoapWebServiceProvider;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 */
@Service
public class DevelopmentSoapWebServiceProvider implements SoapWebServiceProvider {

    @Inject
    private SoapRequestInfoService requestInfo;
    @Inject
    private SoapExceptionThrower exceptionThrower;

    @NotNull
    public List<SoapWebService> getAll() {
        return ImmutableList.<SoapWebService>of(
                requestInfo,
                exceptionThrower
        );
    }
}
