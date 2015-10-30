package com.optimaize.soapworks.client.rest.http;

import com.optimaize.soapworks.common.rest.TypeRef;

import javax.ws.rs.WebApplicationException;
import java.util.Map;

/**
 *
 */
public interface RestHttpClient {

    <T> RestHttpClientResponse<T> invokeGet(String path,
                                            QueryParams queryParams,
                                            HeaderParams headerParams,
                                            String accept, String contentType,
                                            TypeRef returnType
    ) throws WebApplicationException;

    <T> RestHttpClientResponse<T> invokeBody(String path, String method,
                                             QueryParams queryParams,
                                             HeaderParams headerParams,
                                             Object body,
                                             String accept, String contentType,
                                             TypeRef returnType
    ) throws WebApplicationException;

    <T> RestHttpClientResponse<T> invokeForm(String path, String method,
                                             QueryParams queryParams,
                                             HeaderParams headerParams,
                                             Map<String, Object> formParams,
                                             String accept, String contentType,
                                             TypeRef returnType
    ) throws WebApplicationException;

}
