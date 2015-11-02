package com.optimaize.anythingworks.exampleproject.clientlib.soap.services.development.exceptionthrower;

/**
 * @author Fabian Kessler
 */
public class ExceptionThrowerCommands {

    public static ExceptionThrowerAccessDeniedNoSuchAccount accessDeniedNoSuchAccount() {
        return new ExceptionThrowerAccessDeniedNoSuchAccount();
    }

}
