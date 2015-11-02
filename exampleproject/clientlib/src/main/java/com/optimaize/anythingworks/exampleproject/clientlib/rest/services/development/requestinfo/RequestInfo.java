package com.optimaize.anythingworks.exampleproject.clientlib.rest.services.development.requestinfo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 */
public class RequestInfo {

    private String method;
    private String uri;
    private String userAgent;
    private String contentType;
    private String httpAccept;
    private String httpHost;
    private String connection;
    private Boolean transportSecure;

    @JsonCreator
    public RequestInfo(@JsonProperty("method") String method,
                       @JsonProperty("uri") String uri,
                       @JsonProperty("userAgent") String userAgent,
                       @JsonProperty("contentType") String contentType,
                       @JsonProperty("httpAccept") String httpAccept,
                       @JsonProperty("httpHost") String httpHost,
                       @JsonProperty("connection") String connection,
                       @JsonProperty("transportSecure") Boolean transportSecure
    ) {
        this.method = method;
        this.uri = uri;
        this.userAgent = userAgent;
        this.contentType = contentType;
        this.httpAccept = httpAccept;
        this.httpHost = httpHost;
        this.connection = connection;
        this.transportSecure = transportSecure;
    }



    public String getMethod() {
        return method;
    }

    public String getUri() {
        return uri;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public String getContentType() {
        return contentType;
    }

    public String getHttpAccept() {
        return httpAccept;
    }

    public String getHttpHost() {
        return httpHost;
    }

    public String getConnection() {
        return connection;
    }

    public Boolean isTransportSecure() {
        return transportSecure;
    }
}
