package com.optimaize.soapworks.client.rest.http;

import com.google.common.base.Optional;
import org.jetbrains.annotations.NotNull;

import javax.ws.rs.core.Response;
import java.util.*;

/**
 *
 */
public class RestHttpClientResponse<T> {

    @NotNull
    private final Response response;
    private final Optional<T> result;

    public RestHttpClientResponse(@NotNull Response response, Optional<T> result) {
        this.response = response;
        this.result = result;
    }

    @NotNull
    public Response getResponse() {
        return response;
    }

    public Optional<T> getResult() {
        return result;
    }

    /**
     * Shortcut for getClientResponse().getStatusInfo().getStatusCode()
     * @return something like 200
     */
    public int getStatusCode() {
        return response.getStatusInfo().getStatusCode();
    }

    /**
     * Returns it as a map, and because by http protocol definition each key may occur multiple times,
     * the value is a List. It almost always contains exactly one item.
     */
    @NotNull
    public Map<String, List<String>> extractResponseHeaders(Response response) {
        Map<String, List<String>> responseHeaders = new LinkedHashMap<>();
        for (Map.Entry<String, List<Object>> entry: response.getHeaders().entrySet()) {
            List<Object> values = entry.getValue();
            List<String> headers = new ArrayList<>(2);
            for (Object o : values) {
                headers.add(String.valueOf(o));
            }
            responseHeaders.put(entry.getKey(), headers);
        }
        return responseHeaders;
    }

}
