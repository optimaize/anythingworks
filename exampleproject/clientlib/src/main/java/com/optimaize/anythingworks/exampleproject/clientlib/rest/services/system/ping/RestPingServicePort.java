package com.optimaize.anythingworks.exampleproject.clientlib.rest.services.system.ping;

import com.optimaize.anythingworks.client.rest.http.*;
import com.optimaize.anythingworks.common.rest.TypeRef;
import com.optimaize.anythingworks.exampleproject.clientlib.rest.services.RestServicePort;

/**
 *
 */
public class RestPingServicePort implements RestServicePort {

    private static final String accept = "application/json";
    private static final String contentType = "application/json";

    private final RestHttpClient restApiClient;
    private final String servicePath;

    public RestPingServicePort(RestHttpClient restApiClient, String servicePath) {
        this.restApiClient = restApiClient;
        this.servicePath = servicePath;
    }

    public String ping(String apiKey) {
        QueryParams queryParams = QueryParams.create();
        queryParams.add("apiKey", apiKey);

        HeaderParams headerParams = HeaderParams.none();

        TypeRef returnType = new TypeRef<String>() {};
        RestHttpClientResponse<String> response = restApiClient.invokeGet(
                servicePath,
                queryParams, headerParams,
                accept, contentType,
                returnType
        );
        return response.getResult().get();
    }

}
