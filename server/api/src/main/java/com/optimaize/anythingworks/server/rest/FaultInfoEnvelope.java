package com.optimaize.anythingworks.server.rest;

import com.optimaize.anythingworks.common.fault.faultinfo.FaultInfo;

/**
 *
 */
public class FaultInfoEnvelope {

    private boolean success;
    private Object result;
    private FaultInfo error;

    public static FaultInfoEnvelope success(Object result) {
        return new FaultInfoEnvelope(true, result, null);
    }

    public static FaultInfoEnvelope error(FaultInfo error) {
        return new FaultInfoEnvelope(false, null, error);
    }

    private FaultInfoEnvelope(boolean success, Object result, FaultInfo error) {
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

    public FaultInfo getError() {
        return error;
    }
}
