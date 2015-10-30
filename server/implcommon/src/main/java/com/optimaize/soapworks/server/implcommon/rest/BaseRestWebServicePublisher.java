package com.optimaize.soapworks.server.implcommon.rest;

import com.optimaize.soapworks.server.rest.RestWebService;
import com.optimaize.soapworks.server.rest.RestWebServiceProvider;
import com.optimaize.soapworks.server.rest.RestWebServicePublisher;

import java.util.Collection;


/**
 */
public abstract class BaseRestWebServicePublisher implements RestWebServicePublisher {

    @Override
    public void publishServices(RestWebServiceProvider restWebServiceProvider) {
        publishServices(restWebServiceProvider.getAll());
    }

    @Override
    public void publishServices(Collection<RestWebService> restWebService) {
        for (RestWebService webService : restWebService) {
            publishService(webService);
        }
    }

    @Override
    public void publishServicesByProviders(Collection<RestWebServiceProvider> restWebServiceProviders) {
        for (RestWebServiceProvider restWebServiceProvider : restWebServiceProviders) {
            publishServices(restWebServiceProvider);
        }
    }

}
