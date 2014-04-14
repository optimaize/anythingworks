package com.optimaize.soapworks.client.exception;

/**
 * Used in {@link FaultInfo}, tells who's to blame for a {@link ServiceException service exception}.
 *
 * @author fab
 */
public enum Blame {

    CLIENT, SERVER, NETWORK, UNKNOWN;

}
