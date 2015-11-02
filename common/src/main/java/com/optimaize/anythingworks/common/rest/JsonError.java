package com.optimaize.anythingworks.common.rest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * An error object to be sent from the server to the client.
 *
 * It contains the http status code, the associated meaning, and a more detailed error message.
 *
 * Example:
 * errorCode = 401
 * errorMeaning = "Unauthorized"
 * errorText = "Unknown api key: 'foobar'!"
 */
public class JsonError {
    private final int errorCode;
    private final String errorMeaning;
    private final String errorText;

    @JsonCreator
    public JsonError(@JsonProperty("errorCode") int errorCode,
                     @JsonProperty("errorMeaning") String errorMeaning,
                     @JsonProperty("errorText") String errorText) {
        this.errorCode = errorCode;
        this.errorMeaning = errorMeaning;
        this.errorText = errorText;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMeaning() {
        return errorMeaning;
    }

    public String getErrorText() {
        return errorText;
    }
}
