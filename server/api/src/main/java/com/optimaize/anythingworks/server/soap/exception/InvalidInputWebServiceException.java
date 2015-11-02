package com.optimaize.anythingworks.server.soap.exception;

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
 * </ul></p>
 */
@WebFault(name="InvalidInputWebServiceException")
public class InvalidInputWebServiceException extends ClientWebServiceException {

    private static final long serialVersionUID = 1L;


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
        super(FaultBeans.Client.InvalidInput.invalidInput(message));

    }

    public InvalidInputWebServiceException(Throwable cause) {
        this(cause.getMessage(), cause);
    }

}
