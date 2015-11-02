package com.optimaize.anythingworks.server.soap.exception;

/**
 * See http://www.w3.org/TR/2000/NOTE-SOAP-20000508/#_Toc478383510
 *
 * <p>The values in here are not ALL UPPER CASE to be the same as the specification.
 * We could refactor to use a field an internal field with a getter for the correct spelling,
 * but using the correct casing directly seemed easier.</p>
 */
public enum SoapFaultCode {

    //excluded because we don't set this from our user code.
    //VersionMismatch,

    //excluded because we don't set this from our user code.
    //MustUnderstand,

    /**
     * Use this when the client is responsible for the error.
     */
    Client,

    /**
     * This is the default, and thus not really needed here.
     */
    Server;
}
