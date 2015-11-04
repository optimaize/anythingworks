package com.optimaize.anythingworks.client.rest;

import com.optimaize.command4j.lang.Key;

/**
 */
public interface RestKeys {

  /**
   * The factory for creating REST endpoint urls. This is always available and provided
   * by the library. If a custom factory is specified, it is preferred.
   */
  Key<RestPortUrlFactory> REST_PORT_URL_FACTORY = Key.create("restPortUrlFactory", RestPortUrlFactory.class);
}
