package com.optimaize.soapworks.exampleproject.clientlib.rest.services.development.requestinfo;

import com.optimaize.soapworks.client.rest.http.*;
import com.optimaize.soapworks.common.rest.TypeRef;

/**
 *
 */
public class RestRequestInfoPort {

    private static final String accept = "application/json";
    private static final String contentType = "application/json";

    private final RestHttpClient restApiClient;
    private final String servicePath;

    public RestRequestInfoPort(RestHttpClient restApiClient, String servicePath) {
        this.restApiClient = restApiClient;
        this.servicePath = servicePath;
    }

    public RequestInfo call(String apiKey) {
        QueryParams queryParams = QueryParams.create();
        queryParams.add("apiKey", apiKey);

        HeaderParams headerParams = HeaderParams.none();

        TypeRef returnType = new TypeRef<RequestInfo>() {};
        RestHttpClientResponse<RequestInfo> response = restApiClient.invokeGet(
                servicePath,
                queryParams, headerParams,
                accept, contentType,
                returnType
        );
        return response.getResult().get();
    }


}
