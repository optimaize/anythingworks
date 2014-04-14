package com.optimaize.soapworks.client;

/**
 * Dictionary for terms used.
 *
 * <p>Javadoc can link to these values with details explanations, instead of copy/pasting the same limited
 * and possibly outdated text.</p>
 */
public enum Vocabulary {

    /*
    Even though Java enums follow the convention to have all values in UPPER CASE,
    there is no need to follow this pattern here. Use the terms in the case that
    they appear in code.
     */


    /**
     * Such as "/service/ping". Starts with but doesn't end in a slash.
     */
    servicePath,

    /**
     * A Port is a wsdl-generated java service class on the client.
     * It is the equivalent of the service on the server.
     */
    Port,

}
