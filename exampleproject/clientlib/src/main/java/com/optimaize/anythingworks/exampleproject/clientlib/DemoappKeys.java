package com.optimaize.anythingworks.exampleproject.clientlib;

import com.optimaize.command4j.lang.Key;

/**
 * A place of keys used by this Demoapp.
 *
 * @author eike, fabian
 */
public interface DemoappKeys {

    /**
     * The api key is required for any call to the demo services. You
     * must supply a valid key with the {@code mode} argument.
     */
    Key<String> API_KEY = Key.stringKey("apiKey");

}
