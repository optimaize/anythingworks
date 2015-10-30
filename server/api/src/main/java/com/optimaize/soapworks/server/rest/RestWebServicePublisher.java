package com.optimaize.soapworks.server.rest;

import com.optimaize.soapworks.server.soap.SoapWebServicePublisher;

import java.util.Collection;

/**
 * TODO same as for {@link SoapWebServicePublisher}
 *
 *
 */
public interface RestWebServicePublisher {

    void publishServices(RestWebServiceProvider restWebServiceProvider);

    void publishServices(Collection<RestWebService> restWebService);

    void publishServicesByProviders(Collection<RestWebServiceProvider> restWebServiceProviders);

    void publishService(RestWebService restWebService);

}
