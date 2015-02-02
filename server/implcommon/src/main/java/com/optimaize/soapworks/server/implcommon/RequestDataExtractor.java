package com.optimaize.soapworks.server.implcommon;

import com.google.common.base.Optional;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Offers methods to extract commonly used information from the request.
 */
public interface RequestDataExtractor {

    /**
     * From http request headers. Returning the first. Absent if there is none or empty string.
     */
    @NotNull
    Optional<String> requestHeaderUserAgent();

    /**
     * From http request headers. Returning the first. Absent if there is none or empty string.
     */
    @NotNull
    Optional<String> requestHeaderContentType();

    /**
     * From http request headers. Returning the first. Absent if there is none or empty string.
     */
    @NotNull
    Optional<String> requestHeaderAccept();

    /**
     * From http request headers. Returning the first. Absent if there is none or empty string.
     */
    @NotNull
    Optional<String> requestHeaderHost();

    /**
     * From http request headers. Returning the first. Absent if there is none or empty string.
     */
    @NotNull
    Optional<String> requestHeaderConnection();

    /**
     * @return for example "POST"
     */
    @NotNull
    String requestMethod();

    /**
     * Think HTTPs
     */
    boolean wasTransportSecure();

    /**
     * Best effort. Uses reflection.
     * See http://stackoverflow.com/questions/28234303/how-do-i-get-the-full-request-url-in-a-java-jax-ws-webservice-webmethod
     * TODO define whether this returns with query string or not (in case there is one).
     * @return starting with a slash, eg "/my/service/url"
     * @throws RuntimeException in case of failure (api change)
     */
    @NotNull
    String uri();

}
