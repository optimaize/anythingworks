package com.optimaize.soapworks.server.soap.exception;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Base class for exceptions thrown by web services where the Client (not the Server) is to blame.
 *
 * @author Fabian Kessler
 */
public abstract class ClientWebServiceException extends BaseWebServiceException {

    protected static final boolean PROBLEM_REPORTED = false;

    public ClientWebServiceException(@NotNull FaultBean faultBean, @Nullable Throwable cause) {
        super(faultBean, cause);
    }

    public ClientWebServiceException(@NotNull FaultBean faultBean) {
        super(faultBean);
    }
}
