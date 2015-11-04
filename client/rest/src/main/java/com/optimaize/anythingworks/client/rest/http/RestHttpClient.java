package com.optimaize.anythingworks.client.rest.http;

import com.optimaize.anythingworks.common.rest.TypeRef;

import javax.ws.rs.WebApplicationException;
import java.util.Map;

/**
 *
 */
public interface RestHttpClient {

    <T> RestHttpClientResponse<T> invokeGet(String path,
                                            QueryParams queryParams,
                                            HeaderParams headerParams,
                                            TypeRef returnType
    ) throws WebApplicationException;

    <T> RestHttpClientResponse<T> invokeBody(String path, String method,
                                             QueryParams queryParams,
                                             HeaderParams headerParams,
                                             Object body,
                                             TypeRef returnType
    ) throws WebApplicationException;

    <T> RestHttpClientResponse<T> invokeForm(String path, String method,
                                             QueryParams queryParams,
                                             HeaderParams headerParams,
                                             Map<String, Object> formParams,
                                             TypeRef returnType
    ) throws WebApplicationException;

}
