package com.optimaize.soapworks.exampleproject.clientlib.rest.services.development.exceptionthrower;

import com.optimaize.soapworks.client.rest.http.HeaderParams;
import com.optimaize.soapworks.client.rest.http.QueryParams;
import com.optimaize.soapworks.client.rest.http.RestHttpClient;
import com.optimaize.soapworks.common.rest.TypeRef;
import com.optimaize.soapworks.exampleproject.clientlib.rest.services.RestServicePort;

/**
 *
 */
public class RestExceptionThrowerPort implements RestServicePort {

    private static final String accept = "application/json";
    private static final String contentType = "application/json";

    private final RestHttpClient restApiClient;
    private final String servicePath;

    public RestExceptionThrowerPort(RestHttpClient restApiClient, String servicePath) {
        this.restApiClient = restApiClient;
        this.servicePath = servicePath;
    }

    public Void call(String apiKey, String exceptionType) {
        QueryParams queryParams = QueryParams.create();
        queryParams.add("apiKey", apiKey);
        queryParams.add("exceptionType", exceptionType);

        HeaderParams headerParams = HeaderParams.none();

        TypeRef returnType = new TypeRef<Void>() {};
        restApiClient.invokeGet(
                servicePath,
                queryParams, headerParams,
                accept, contentType,
                returnType
        );
        return null;
    }

}
