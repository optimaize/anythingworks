package com.optimaize.anythingworks.server.rest;

import com.optimaize.anythingworks.common.rest.JsonError;

/**
 *
 */
@Deprecated
public class JsonErrorEnvelope {

    private boolean success;
    private Object result;
    private JsonError error;

    public static JsonErrorEnvelope success(Object result) {
        return new JsonErrorEnvelope(true, result, null);
    }

    public static JsonErrorEnvelope error(JsonError jsonError) {
        return new JsonErrorEnvelope(false, null, jsonError);
    }

    private JsonErrorEnvelope(boolean success, Object result, JsonError error) {
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
