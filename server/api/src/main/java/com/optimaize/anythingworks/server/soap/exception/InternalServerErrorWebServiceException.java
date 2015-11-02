package com.optimaize.anythingworks.server.soap.exception;

import org.jetbrains.annotations.NotNull;
import javax.xml.ws.WebFault;

/**
 * Thrown when there was an internal server exception that should not have happened.
 *
 * <p>In HTTP Status Code terms this can mean:
 *   <li>500 Internal Server Error</li>
 *   <li>502 Bad Gateway</li>
 * </p>
 *
 * <p>This includes the cases:
 * <ul>
 *   <li>OutOfMemoryError<pre>
 *     Cause:  Misconfiguration of caches, memory leaks.
 *     Action: 1) Application should auto-restart (hint: use Tanuki). That solves it for a while.
 *             2) The provider should analyze the memory snapshot and try to figure out the problem.
 *     Retry:  The client may resend the same request again later.
 *     </pre>
 *   </li>
 *   <li>IllegalArgumentException<pre>
 *     Cause:        Some internal method refused to work with a given input parameter.
 *      Action:      The provider should analyze the situation (offending method and parameter) and fix
 *                   the problem by changing code or data, then deploy a patch.
 *      Retry:       The client should not resend the same request for it would result in the same
 *                   situation.
 *     </pre>
 *   </li>
 *   <li>UnsupportedOperationException<pre>
 *      Cause:       Similar to IllegalArgumentException.
 *      Action:      The provider should analyze the situation (offending method and parameter) and fix
 *                   the problem by changing code or data, then deploy a patch.
 *      Retry:       The client should not resend the same request for it would result in the same
 *                   situation.
 *     </pre>
 *   </li>
 *
 *   <li>AssertionError<pre>
 *      Cause:       An unexpected situation that really did happen in code, and the case was not detected
 *                   by unit and integration tests.
 *      Action:      The provider should analyze the situation (offending method and variables) and fix
 *                   the problem by changing code or data, then deploy a patch.
 *      Retry:       The client should not resend the same request for it would result in the same
 *                   situation.
 *     </pre>
 *   </li>
 * </ul>
 *
 * @author Fabian Kessler
 */
@WebFault(name="InternalServerErrorWebServiceException")
public class InternalServerErrorWebServiceException extends ServerWebServiceException {

    private static final long serialVersionUID = 1L;

    private static final String FAULT_CAUSE = "InternalServerError";


    public InternalServerErrorWebServiceException(@NotNull FaultBean faultBean, Throwable cause) {
        super(faultBean, cause);
    }

    public InternalServerErrorWebServiceException(@NotNull FaultBean faultBean) {
        super(faultBean);
    }


//    public InternalServerErrorWebServiceException(@NotNull Retry retryThisServer, boolean problemReported) {
//        this("Internal server error", retryThisServer, problemReported);
//    }
//    public InternalServerErrorWebServiceException(@NotNull String message, @NotNull Retry retryThisServer, boolean problemReported) {
//        super(
//                FaultBeans.Server.AccessDenied.tooManyConcurrentRequests(userId)
//                new FaultBean(1100, BLAME, FAULT_CAUSE, message, retryThisServer, Retry.now(), problemReported));
//    }
//    public InternalServerErrorWebServiceException(@NotNull Throwable cause, @NotNull Retry retryThisServer, boolean problemReported) {
//        this(cause.getMessage(), cause, retryThisServer, problemReported);
//    }
//    public InternalServerErrorWebServiceException(@NotNull String message, @NotNull Throwable cause, @NotNull Retry retryThisServer, boolean problemReported) {
//        super(new FaultBean(1100, BLAME, FAULT_CAUSE, message, retryThisServer, Retry.now(), problemReported), cause);
//    }

}
