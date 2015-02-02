package com.optimaize.soapworks.server.implcommon;

import com.google.common.base.Optional;
import org.jetbrains.annotations.NotNull;

import javax.xml.ws.WebServiceContext;

/**
 * Use this if you need to pass the information around to other threads.
 * The instance must be created in the original Thread.
 */
public class EagerRequestDataExtractor implements RequestDataExtractor {

    private final Optional<String> userAgent;
    private final Optional<String> contentType;
    private final Optional<String> accept;
    private final Optional<String> host;
    private final Optional<String> connection;
    private final String requestMethod;
    private final boolean wasTransportSecure;
    private final String uri;

    public EagerRequestDataExtractor(WebServiceContext webServiceContext) {
        try {
            LazyRequestDataExtractor lazy = new LazyRequestDataExtractor(webServiceContext);
            userAgent = lazy.requestHeaderUserAgent();
            contentType = lazy.requestHeaderContentType();
            accept = lazy.requestHeaderAccept();
            host = lazy.requestHeaderHost();
            connection = lazy.requestHeaderConnection();
            requestMethod = lazy.requestMethod();
            wasTransportSecure = lazy.wasTransportSecure();
            uri = lazy.uri();
        } catch (IllegalStateException e) {
            throw new IllegalStateException("Only the Thread accepting the request can create it!", e);
        }
    }



    @NotNull
    @Override
    public Optional<String> requestHeaderUserAgent() {
        return userAgent;
    }

    @NotNull
    @Override
    public Optional<String> requestHeaderContentType() {
        return contentType;
    }

    @NotNull
    @Override
    public Optional<String> requestHeaderAccept() {
        return accept;
    }

    @NotNull
    @Override
    public Optional<String> requestHeaderHost() {
        return host;
    }

    @NotNull
    @Override
    public Optional<String> requestHeaderConnection() {
        return connection;
    }

    @NotNull
    @Override
    public String requestMethod() {
        return requestMethod;
    }

    @Override
    public boolean wasTransportSecure() {
        return wasTransportSecure;
    }

    @NotNull
    @Override
    public String uri() {
        return uri;
    }
}
