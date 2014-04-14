package com.optimaize.soapworks.client.exception;

import java.io.Serializable;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Tells more about who's to blame and what can be done.
 *
 * @author fab
 */
public class FaultInfo implements Serializable { //<- must be serializable, because exceptions are

    @NotNull
    private final Blame blame;
    @NotNull
    private final Retry retry;
    @Nullable
    private final Boolean problemLoggedOnServer;

    public FaultInfo(@NotNull Blame blame, @NotNull Retry retry, @Nullable Boolean problemLoggedOnServer) {
        this.blame = blame;
        this.retry = retry;
        this.problemLoggedOnServer = problemLoggedOnServer;
    }

    @NotNull
    public Blame getBlame() {
        return blame;
    }

    @NotNull
    public Retry getRetry() {
        return retry;
    }

    /**
     * Tells if the exception was logged on the server for further investigation by the system admin,
     * developers or data team.
     * @return <code>null</code> for unknown.
     */
    @Nullable
    public Boolean isProblemLoggedOnServer() {
        return problemLoggedOnServer;
    }
}
