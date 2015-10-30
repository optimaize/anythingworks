package com.optimaize.soapworks.server.rest;

import com.optimaize.soapworks.common.rest.JsonError;

/**
 *
 */
public class Envelope {

    private boolean success;
    private Object result;
    private JsonError error;

    public static Envelope success(Object result) {
        return new Envelope(true, result, null);
    }

    public static Envelope error(JsonError jsonError) {
        return new Envelope(false, null, jsonError);
    }

    private Envelope(boolean success, Object result, JsonError error) {
        this.success = success;
        this.result = result;
        this.error = error;
    }

    public boolean isSuccess() {
        return success;
    }

    public Object getResult() {
        return result;
    }

    public JsonError getError() {
        return error;
    }
}
