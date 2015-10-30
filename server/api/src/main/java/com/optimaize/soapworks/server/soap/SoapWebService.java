package com.optimaize.soapworks.server.soap;

import org.jetbrains.annotations.NotNull;

/**
 * Basic interface for classes annotated with @WebService.
 */
public interface SoapWebService {

    /**
     * The service-specific url path for the service.
     *
     * <p>Because of the dynamic way the services are published to the web server,
     * this approach is used. It may be possible to do this with standard annotations
     * on the class instead. Feel free to change if you're an expert in this field.</p>
     *
     * <p>Note: implementations must add the annotation @WebMethod(exclude=true)</p>
     *
     * @return Does not start nor end with a slash. Examples: "foo" or "foo/bar".
     */
    @NotNull
    String getServicePath();

}
