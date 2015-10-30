package com.optimaize.soapworks.exampleproject.clientapp;

import com.optimaize.command4j.CommandExecutor;
import com.optimaize.command4j.Mode;
import com.optimaize.soapworks.client.soap.exception.AccessDeniedServiceException;
import com.optimaize.soapworks.exampleproject.clientlib.DemoappModeFactory;
import com.optimaize.soapworks.exampleproject.clientlib.DemoappRemoteExecutors;
import com.optimaize.soapworks.exampleproject.clientlib.rest.services.development.exceptionthrower.RestExceptionThrowerCommand;
import com.optimaize.soapworks.exampleproject.clientlib.soap.services.development.exceptionthrower.ExceptionThrowerAccessDeniedNoSuchAccount;
import org.testng.annotations.Test;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.WebApplicationException;

/**
 * In order to run these tests, run the Boot class first.
 *
 * @author Fabian Kessler
 */
public class ExceptionThrowerTest {

    private final CommandExecutor executor = DemoappRemoteExecutors.get();

    @Test(expectedExceptions=AccessDeniedServiceException.class)
    public void soap_accessDeniedNoSuchAccount() throws Exception {
        ExceptionThrowerAccessDeniedNoSuchAccount command = new ExceptionThrowerAccessDeniedNoSuchAccount();
        Mode mode = DemoappModeFactory.unitTest();
        executor.execute(command, mode, null).orNull();
    }



    @Test(expectedExceptions=NotAuthorizedException.class)
    public void rest_NotAuthorized() throws Exception {
        RestExceptionThrowerCommand command = new RestExceptionThrowerCommand();
        Mode mode = DemoappModeFactory.unitTest();
        executor.execute(command, mode, "NotAuthorized").orNull();
    }

    @Test(expectedExceptions=NotAuthorizedException.class)
    public void rest_Forbidden() throws Exception {
        RestExceptionThrowerCommand command = new RestExceptionThrowerCommand();
        Mode mode = DemoappModeFactory.unitTest();
        executor.execute(command, mode, "Forbidden").orNull();
    }

    @Test(expectedExceptions=BadRequestException.class)
    public void rest_BadRequest() throws Exception {
        RestExceptionThrowerCommand command = new RestExceptionThrowerCommand();
        Mode mode = DemoappModeFactory.unitTest();
        executor.execute(command, mode, "BadRequest").orNull();
    }

    @Test(expectedExceptions=InternalServerErrorException.class)
    public void rest_InternalServerError() throws Exception {
        RestExceptionThrowerCommand command = new RestExceptionThrowerCommand();
        Mode mode = DemoappModeFactory.unitTest();
        executor.execute(command, mode, "InternalServerError").orNull();
    }

}
