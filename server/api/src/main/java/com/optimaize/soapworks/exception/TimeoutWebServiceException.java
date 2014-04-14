package com.optimaize.soapworks.exception;

import javax.xml.ws.WebFault;

/**
 * Thrown when there was a timeout somewhere.
 *
 * <p>Possible places:
 * <ul>
 *   <li>between client and server</li>
 *   <li>between server gateway and target web service handler</li>
 * </ul></p>
 *
 * <p>Possible causes:
 * <ul>
 *   <li>client has no or laggy internet</li>
 *   <li>server has no or laggy internet</li>
 *   <li>some dns lookup delay or failure</li>
 *   <li>one party was impatient and gave up waiting</li>
 * </ul></p>
 */
@Deprecated
//@WebFault(name="ServiceUnavailableWebServiceException")
public class TimeoutWebServiceException /*extends BaseWebServiceException*/ {

//    private static final long serialVersionUID = 1L;
//
//    private static final Blame BLAME = Blame.NETWORK;
//
//    public TimeoutWebServiceException(String message) {
//        super(new FaultBean(BLAME, RetryType.NOW, false));
//    }

}
