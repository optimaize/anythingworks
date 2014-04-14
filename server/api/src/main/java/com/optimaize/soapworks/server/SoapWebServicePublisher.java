package com.optimaize.soapworks.server;

import java.util.Collection;

/**
 * TODO this is a very basic interface atm an does not answer the following questions:
 *  - what if web server is started already, and impl doesn't support adding endpoints after
 *    start? probably allow the methods to throw IllegalStateException.
 *  - what if the same service is published already?
 *    probably allow the impl to change to the new service, or throw IllegalStateException
 *  - note: UnsupportedOperationException should probably not be used when it depends on the
 *    state if the impl supports it or not.
 *  - who starts/stops the server? jdk does automatically, grizzly doesn't. should this
 *    interface be concerned about it?
 *
 *
 */
public interface SoapWebServicePublisher {

    void publishServices(SoapWebServiceProvider soapWebServiceProvider);

    void publishServices(Collection<SoapWebService> soapWebService);

    void publishServicesByProviders(Collection<SoapWebServiceProvider> soapWebServiceProviders);

    void publishService(SoapWebService soapWebService);

}
