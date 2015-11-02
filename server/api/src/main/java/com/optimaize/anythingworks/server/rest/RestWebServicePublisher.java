package com.optimaize.anythingworks.server.rest;

import com.optimaize.anythingworks.server.soap.SoapWebServicePublisher;

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
