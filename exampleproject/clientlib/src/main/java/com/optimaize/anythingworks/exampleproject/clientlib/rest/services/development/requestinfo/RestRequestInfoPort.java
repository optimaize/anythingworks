package com.optimaize.anythingworks.exampleproject.clientlib.rest.services.development.requestinfo;

import com.optimaize.anythingworks.client.rest.http.*;
import com.optimaize.anythingworks.common.rest.TypeRef;
import com.optimaize.anythingworks.exampleproject.clientlib.rest.services.RestServicePort;

/**
 *
 */
public class RestRequestInfoPort extends RestServicePort {

    private static final TypeRef returnType = new TypeRef<RequestInfo>() {};

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

        RestHttpClientResponse<RequestInfo> response = restApiClient.invokeGet(
                servicePath,
                queryParams, headerParams,
                returnType
        );
        return response.getResult().get();
    }

}
