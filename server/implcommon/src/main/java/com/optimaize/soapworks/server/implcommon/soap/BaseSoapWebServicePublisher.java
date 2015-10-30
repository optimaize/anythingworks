package com.optimaize.soapworks.server.implcommon.soap;

import com.optimaize.soapworks.server.soap.SoapWebService;
import com.optimaize.soapworks.server.soap.SoapWebServiceProvider;
import com.optimaize.soapworks.server.soap.SoapWebServicePublisher;

import java.util.Collection;


/**
 */
public abstract class BaseSoapWebServicePublisher implements SoapWebServicePublisher {

    @Override
    public void publishServices(SoapWebServiceProvider soapWebServiceProvider) {
        publishServices(soapWebServiceProvider.getAll());
    }

    @Override
    public void publishServices(Collection<SoapWebService> soapWebService) {
        for (SoapWebService webService : soapWebService) {
            publishService(webService);
        }
    }

    @Override
    public void publishServicesByProviders(Collection<SoapWebServiceProvider> soapWebServiceProviders) {
        for (SoapWebServiceProvider soapWebServiceProvider : soapWebServiceProviders) {
            publishServices(soapWebServiceProvider);
        }
    }

}
