package com.optimaize.soapworks.server.exception;

import org.jetbrains.annotations.NotNull;

import javax.xml.ws.WebFault;

/**
 * Thrown to reject the user input.
 *
 * <p>In HTTP Status Code terms this can mean:
 * <ul>
 *   <li>400 Bad Request</li>
 * </ul></p>
 *
 * <p>Possible causes:
 * <ul>
 *   <li>illegal argument</li>
 *   <li>incomplete request</li>
 *   <li>
 *       wrong api use
 *       <ul>
 *           <li>illegal state</li>
 *           <li>senseless request (for example when asking for the gender when providing it already)</li>
 *       </ul>
 *   </li>
 * </ul></p>
 */
@WebFault(name="InvalidInputWebServiceException")
public class InvalidInputWebServiceException extends ClientWebServiceException {

    private static final long serialVersionUID = 1L;

    private static final Blame BLAME = Blame.CLIENT;
    public static final String FAULT_CAUSE = "InvalidInput";


    public InvalidInputWebServiceException(@NotNull FaultBean faultBean, Throwable cause) {
        super(faultBean, cause);
    }
    public InvalidInputWebServiceException(@NotNull FaultBean faultBean) {
        super(faultBean);
    }

    public InvalidInputWebServiceException(String message) {
        this(message, createSfe(SoapFaultCode.Client));
    }

    public InvalidInputWebServiceException(String message, Throwable cause) {
        //TODO stick SoapFaultCode.Client into cause (dunno how)
        super(new FaultBean(2200, BLAME, FAULT_CAUSE, message, Retry.no(), Retry.no(), PROBLEM_REPORTED));

    }

    public InvalidInputWebServiceException(Throwable cause) {
        this(cause.getMessage(), cause);
    }

}
