package com.optimaize.anythingworks.exampleproject.server.services.rest.development;

import com.google.common.collect.ImmutableList;
import com.optimaize.anythingworks.exampleproject.server.services.rest.development.exceptionthrower.RestExceptionThrower;
import com.optimaize.anythingworks.exampleproject.server.services.rest.development.post.RestPostService;
import com.optimaize.anythingworks.exampleproject.server.services.rest.development.requestinfo.RestRequestInfoService;
import com.optimaize.anythingworks.server.rest.RestWebService;
import com.optimaize.anythingworks.server.rest.RestWebServiceProvider;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 */
@Service
public class RestDevelopmentWebServiceProvider implements RestWebServiceProvider {

    @Inject
    private RestExceptionThrower exceptionThrower;
    @Inject
    private RestPostService restPostService;
    @Inject
    private RestRequestInfoService requestInfoService;

    @NotNull
    public List<RestWebService> getAll() {
        return ImmutableList.<RestWebService>of(
                exceptionThrower,
                restPostService,
                requestInfoService
        );
    }
}
