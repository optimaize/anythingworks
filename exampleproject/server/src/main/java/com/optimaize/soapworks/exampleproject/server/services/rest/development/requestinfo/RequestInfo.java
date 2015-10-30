package com.optimaize.soapworks.exampleproject.server.services.rest.development.requestinfo;

/**
 *
 */
public class RequestInfo {

    public static class Builder {
        private String method;
        private String uri;
        private String userAgent;
        private String contentType;
        private String httpAccept;
        private String httpHost;
        private String connection;
        private Boolean transportSecure;

        public Builder method(String method) {
            this.method = method;
            return this;
        }
        public Builder uri(String uri) {
            this.uri = uri;
            return this;
        }
        public Builder userAgent(String userAgent) {
            this.userAgent = userAgent;
            return this;
        }
        public Builder contentType(String contentType) {
            this.contentType = contentType;
            return this;
        }
        public Builder httpAccept(String httpAccept) {
            this.httpAccept = httpAccept;
            return this;
        }
        public Builder httpHost(String httpHost) {
            this.httpHost = httpHost;
            return this;
        }
        public Builder connection(String connection) {
            this.connection = connection;
            return this;
        }
        public Builder transportSecure(Boolean transportSecure) {
            this.transportSecure = transportSecure;
            return this;
        }
        public RequestInfo build() {
            return new RequestInfo(
                    method, uri, userAgent, contentType, httpAccept, httpHost, connection, transportSecure
            );
        }
    }


    private String method;
    private String uri;
    private String userAgent;
    private String contentType;
    private String httpAccept;
    private String httpHost;
    private String connection;
    private Boolean transportSecure;

    public RequestInfo(String method, String uri, String userAgent, String contentType, String httpAccept, String httpHost, String connection, Boolean transportSecure) {
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
