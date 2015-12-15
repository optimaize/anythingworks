package com.optimaize.anythingworks.exampleproject.clientlib.rest.services.development.exceptionthrower;

import com.optimaize.anythingworks.client.rest.http.HeaderParams;
import com.optimaize.anythingworks.client.rest.http.QueryParams;
import com.optimaize.anythingworks.client.rest.http.RestHttpClient;
import com.optimaize.anythingworks.client.rest.http.RestHttpClientResponse;
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

    public String call(String apiKey, String exceptionType, int exceptionChance) {
        QueryParams queryParams = QueryParams.create();
        queryParams.add("apiKey", apiKey);
        queryParams.add("exceptionType", exceptionType);
        queryParams.add("exceptionChance", ""+exceptionChance);

        HeaderParams headerParams = HeaderParams.none();

        RestHttpClientResponse<String> response = restApiClient.invokeGet(
                servicePath,
                queryParams, headerParams,
                returnType
        );
        return response.getResult().get();
    }

}
