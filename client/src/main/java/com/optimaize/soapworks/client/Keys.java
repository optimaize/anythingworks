package com.optimaize.soapworks.client;

import com.optimaize.command4j.lang.Key;
import com.optimaize.soapworks.client.rest.RestPortUrlFactory;
import com.optimaize.soapworks.client.soap.SoapPortUrlFactory;
import com.optimaize.soapworks.common.host.Host;

/**
 * @author Eike Kettner
 */
public interface Keys {

  // ~~~~ port creation/caching strategy

  // TODO fab: change to use Host class.

  // the port is created from an URL. The url is comprised of a url base and the
  // service path. The service path is known by the commands themselves. The url base
  // can be specified by either the library or the user.
  // One command call can only go to one endpoint/port. So we can put the url-base
  // into the mode. If the user does not specify one, a default host is provided by
  // the library. Retry-Interceptors could then iterate through a simple set of
  // hosts and add them to the mode before each try.

  /**
   * The host for the service endpoints.
   */
  Key<Host> HOST = Key.create("host", Host.class);

  /**
   * The factory for creating SOAP endpoint urls. This is always available and provided
   * by the library. If a custom factory is specified, it is preferred.
   */
  Key<SoapPortUrlFactory> SOAP_PORT_URL_FACTORY = Key.create("soapPortUrlFactory", SoapPortUrlFactory.class);

  /**
   * The factory for creating REST endpoint urls. This is always available and provided
   * by the library. If a custom factory is specified, it is preferred.
   */
  Key<RestPortUrlFactory> REST_PORT_URL_FACTORY = Key.create("restPortUrlFactory", RestPortUrlFactory.class);
}
