package com.optimaize.anythingworks.client.soap;

import com.optimaize.command4j.lang.Key;

/**
 * @author Eike Kettner
 */
public interface SoapKeys {

  /**
   * The factory for creating SOAP endpoint urls. This is always available and provided
   * by the library. If a custom factory is specified, it is preferred.
   */
  Key<SoapPortUrlFactory> SOAP_PORT_URL_FACTORY = Key.create("soapPortUrlFactory", SoapPortUrlFactory.class);

}
