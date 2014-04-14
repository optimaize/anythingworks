package com.optimaize.soapworks.common.host;

/**
 * The protocol for the network connection (HTTP, HTTPS).
 */
public enum Protocol {

    /**
     * Has default port 80.
     */
    HTTP   (80),
    /**
     * Has default port 443.
     */
    HTTPS (443);

    private final int defaultPortNumber;

    private Protocol(int defaultPortNumber) {
        this.defaultPortNumber = defaultPortNumber;
    }

    /**
     * @return the default port number for the protocol such as 80 for HTTP.
     */
    public int getDefaultPortNumber() {
        return defaultPortNumber;
    }

}
