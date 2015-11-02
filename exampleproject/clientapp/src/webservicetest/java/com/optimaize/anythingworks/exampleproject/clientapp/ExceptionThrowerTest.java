package com.optimaize.anythingworks.exampleproject.clientapp;

import com.optimaize.command4j.CommandExecutor;
import com.optimaize.command4j.Mode;
import com.optimaize.anythingworks.client.soap.exception.AccessDeniedServiceException;
import com.optimaize.anythingworks.exampleproject.clientlib.DemoappModeFactory;
import com.optimaize.anythingworks.exampleproject.clientlib.DemoappRemoteExecutors;
import com.optimaize.anythingworks.exampleproject.clientlib.rest.services.development.exceptionthrower.ExceptionThrowerParams;
import com.optimaize.anythingworks.exampleproject.clientlib.rest.services.development.exceptionthrower.RestExceptionThrowerCommand;
import com.optimaize.anythingworks.exampleproject.clientlib.soap.services.development.exceptionthrower.ExceptionThrowerAccessDeniedNoSuchAccount;
import org.testng.annotations.Test;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotAuthorizedException;

import static org.testng.Assert.assertEquals;

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
        executor.execute(command, mode, new ExceptionThrowerParams(ExceptionThrowerParams.ExceptionType.NotAuthorized, 100)).orNull();
    }

    @Test(expectedExceptions=NotAuthorizedException.class)
    public void rest_Forbidden() throws Exception {
        RestExceptionThrowerCommand command = new RestExceptionThrowerCommand();
        Mode mode = DemoappModeFactory.unitTest();
        executor.execute(command, mode, new ExceptionThrowerParams(ExceptionThrowerParams.ExceptionType.Forbidden, 100)).orNull();
    }

    @Test(expectedExceptions=BadRequestException.class)
    public void rest_BadRequest() throws Exception {
        RestExceptionThrowerCommand command = new RestExceptionThrowerCommand();
        Mode mode = DemoappModeFactory.unitTest();
        executor.execute(command, mode, new ExceptionThrowerParams(ExceptionThrowerParams.ExceptionType.BadRequest, 100)).orNull();
    }

    @Test(expectedExceptions=InternalServerErrorException.class)
    public void rest_InternalServerError() throws Exception {
        RestExceptionThrowerCommand command = new RestExceptionThrowerCommand();
        Mode mode = DemoappModeFactory.unitTest();
        executor.execute(command, mode, new ExceptionThrowerParams(ExceptionThrowerParams.ExceptionType.InternalServerError, 100)).orNull();
    }

    /**
     * Because the chance for an exception to be thrown is set to zero, all calls succeed.
     */
    @Test
    public void rest_InternalServerError_zeroChance() throws Exception {
        RestExceptionThrowerCommand command = new RestExceptionThrowerCommand();
        Mode mode = DemoappModeFactory.unitTest();
        String result = executor.execute(command, mode, new ExceptionThrowerParams(ExceptionThrowerParams.ExceptionType.InternalServerError, 0)).orNull();
        assertEquals(result, "OK");
    }

}
