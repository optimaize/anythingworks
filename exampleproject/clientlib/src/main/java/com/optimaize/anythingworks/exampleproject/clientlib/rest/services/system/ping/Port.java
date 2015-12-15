package com.optimaize.anythingworks.exampleproject.clientlib.rest.services.system.ping;

import com.optimaize.anythingworks.client.rest.http.*;
import com.optimaize.anythingworks.common.rest.TypeRef;
import com.optimaize.anythingworks.exampleproject.clientlib.rest.services.RestServicePort;

/**
 *
 */
class Port extends RestServicePort {

    private static final TypeRef returnType = new TypeRef<String>() {};

    private final RestHttpClient restApiClient;
    private final String servicePath;

    public Port(RestHttpClient restApiClient, String servicePath) {
        this.restApiClient = restApiClient;
        this.servicePath = servicePath;
    }

    public String ping(String apiKey) {
        QueryParams queryParams = QueryParams.create();
        queryParams.add("apiKey", apiKey);

        HeaderParams headerParams = HeaderParams.none();

        RestHttpClientResponse<String> response = restApiClient.invokeGet(
                servicePath,
                queryParams, headerParams,
                returnType
        );
        return response.getResult().get();
    }

}
