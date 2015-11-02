package com.optimaize.anythingworks.exampleproject.clientlib.rest.services.development.post;

import com.optimaize.anythingworks.client.rest.http.*;
import com.optimaize.anythingworks.common.rest.TypeRef;
import com.optimaize.anythingworks.exampleproject.clientlib.rest.services.RestServicePort;
import com.optimaize.anythingworks.exampleproject.ontology.rest.development.post.ComplexObject;

/**
 *
 */
public class RestPostServicePort implements RestServicePort {

    private static final String accept = "application/json";
    private static final String contentType = "application/json";

    private final RestHttpClient restApiClient;
    private final String servicePath;

    public RestPostServicePort(RestHttpClient restApiClient, String servicePath) {
        this.restApiClient = restApiClient;
        this.servicePath = servicePath;
    }

    public ComplexObject post(String apiKey, ComplexObject complexParam) {
        Object postBody = complexParam;

        QueryParams queryParams = QueryParams.create();
        queryParams.add("apiKey", apiKey);

        HeaderParams headerParams = HeaderParams.none();

        TypeRef returnType = new TypeRef<ComplexObject>() {};
        RestHttpClientResponse<ComplexObject> response = restApiClient.invokeBody(
                servicePath, "POST",
                queryParams, headerParams,
                postBody,
                accept, contentType,
                returnType
        );
        return response.getResult().get();
    }

}
