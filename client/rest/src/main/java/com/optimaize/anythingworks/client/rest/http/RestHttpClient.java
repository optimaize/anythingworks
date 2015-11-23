package com.optimaize.anythingworks.client.rest.http;

import com.optimaize.anythingworks.common.fault.exceptions.ServiceException;
import com.optimaize.anythingworks.common.rest.TypeRef;

import java.util.Map;

/**
 *
 */
public interface RestHttpClient {

    <T> RestHttpClientResponse<T> invokeGet(String path,
                                            QueryParams queryParams,
                                            HeaderParams headerParams,
                                            TypeRef returnType
    ) throws ServiceException;

    <T> RestHttpClientResponse<T> invokeBody(String path, String method,
                                             QueryParams queryParams,
                                             HeaderParams headerParams,
                                             Object body,
                                             TypeRef returnType
    ) throws ServiceException;

    <T> RestHttpClientResponse<T> invokeForm(String path, String method,
                                             QueryParams queryParams,
                                             HeaderParams headerParams,
                                             Map<String, Object> formParams,
                                             TypeRef returnType
    ) throws ServiceException;

}
