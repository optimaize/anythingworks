package com.optimaize.anythingworks.exampleproject.clientlib.rest.services.development.post;

import com.optimaize.anythingworks.client.rest.http.*;
import com.optimaize.anythingworks.common.rest.TypeRef;
import com.optimaize.anythingworks.exampleproject.clientlib.rest.services.RestServicePort;
import com.optimaize.anythingworks.exampleproject.ontology.rest.development.post.ComplexObject;

/**
 *
 */
public class RestPostServicePort extends RestServicePort {

    private static final TypeRef returnType = new TypeRef<ComplexObject>() {};

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

        RestHttpClientResponse<ComplexObject> response = restApiClient.invokeBody(
                servicePath, "POST",
                queryParams, headerParams,
                postBody,
                returnType
        );
        return response.getResult().get();
    }

}
