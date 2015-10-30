package com.optimaize.soapworks.server.soap.exception;

import com.optimaize.soapworks.common.soap.exception.Blame;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Base class for exceptions thrown by web services where the Server (not the Client) is to blame.
 *
 * @author Fabian Kessler
 */
public abstract class ServerWebServiceException extends BaseWebServiceException {

    protected static final Blame BLAME = Blame.SERVER;

    public ServerWebServiceException(@NotNull FaultBean faultBean, @Nullable Throwable cause) {
        super(faultBean, cause);
    }

    public ServerWebServiceException(@NotNull FaultBean faultBean) {
        super(faultBean);
    }
}
