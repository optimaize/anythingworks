package com.optimaize.anythingworks.exampleproject.clientapp;

import com.optimaize.anythingworks.common.fault.exceptions.AccessDeniedServiceException;
import com.optimaize.anythingworks.common.fault.exceptions.BadRequestServiceException;
import com.optimaize.anythingworks.common.fault.exceptions.InternalServerErrorServiceException;
import com.optimaize.anythingworks.common.fault.faultinfo.Blame;
import com.optimaize.anythingworks.common.fault.faultinfo.RetryType;
import com.optimaize.anythingworks.exampleproject.clientlib.DemoappModeFactory;
import com.optimaize.anythingworks.exampleproject.clientlib.DemoappRemoteExecutors;
import com.optimaize.anythingworks.exampleproject.clientlib.rest.services.development.exceptionthrower.ExceptionThrowerParams;
import com.optimaize.anythingworks.exampleproject.clientlib.rest.services.development.exceptionthrower.RestExceptionThrowerCommand;
import com.optimaize.anythingworks.exampleproject.clientlib.soap.services.development.exceptionthrower.SoapExceptionThrowerCommand;
import com.optimaize.command4j.CommandExecutor;
import com.optimaize.command4j.Mode;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

/**
 * In order to run these tests, run the Boot class first.
 *
 * @author Fabian Kessler
 */
public class ExceptionThrowerTest {

    private final CommandExecutor executor = DemoappRemoteExecutors.get();

    @Test
    public void soap_AccessDenied_NoSuchAccount() throws Exception {
        SoapExceptionThrowerCommand command = new SoapExceptionThrowerCommand();
        Mode mode = DemoappModeFactory.unitTest();
        try {
            executor.execute(command, mode, new com.optimaize.anythingworks.exampleproject.clientlib.soap.services.development.exceptionthrower.ExceptionThrowerParams(com.optimaize.anythingworks.exampleproject.clientlib.soap.services.development.exceptionthrower.ExceptionThrowerParams.ExceptionType.NoSuchAccount, 100)).orNull();
            fail("Expected exception!");
        } catch (AccessDeniedServiceException e) {
            assertEquals(e.getFaultInfo().getBlame(), Blame.CLIENT);
            assertEquals(e.getFaultInfo().getRetrySameLocation().get().getRetryType(), RetryType.NO);
            assertEquals(e.getFaultInfo().getRetryOtherLocations().get().getRetryType(), RetryType.NO);
        }
    }

    @Test
    public void soap_BadRequest_InvalidInput() throws Exception {
        SoapExceptionThrowerCommand command = new SoapExceptionThrowerCommand();
        Mode mode = DemoappModeFactory.unitTest();
        try {
            executor.execute(command, mode, new com.optimaize.anythingworks.exampleproject.clientlib.soap.services.development.exceptionthrower.ExceptionThrowerParams(com.optimaize.anythingworks.exampleproject.clientlib.soap.services.development.exceptionthrower.ExceptionThrowerParams.ExceptionType.InvalidInput, 100)).orNull();
            fail("Expected exception!");
        } catch (BadRequestServiceException e) {
            assertEquals(e.getFaultInfo().getBlame(), Blame.CLIENT);
            assertEquals(e.getFaultInfo().getRetrySameLocation().get().getRetryType(), RetryType.NO);
            assertEquals(e.getFaultInfo().getRetryOtherLocations().get().getRetryType(), RetryType.NO);
        }
    }



    @Test
    public void rest_AccessDenied_NoSuchAccount() throws Exception {
        RestExceptionThrowerCommand command = new RestExceptionThrowerCommand();
        Mode mode = DemoappModeFactory.unitTest();
        try {
            executor.execute(command, mode, new ExceptionThrowerParams(ExceptionThrowerParams.ExceptionType.NoSuchAccount, 100)).orNull();
        } catch (AccessDeniedServiceException e) {
            assertEquals(e.getFaultInfo().getBlame(), Blame.CLIENT);
            assertEquals(e.getFaultInfo().getRetrySameLocation().get().getRetryType(), RetryType.NO);
            assertEquals(e.getFaultInfo().getRetryOtherLocations().get().getRetryType(), RetryType.NO);
        }
    }

    @Test(expectedExceptions=BadRequestServiceException.class)
    public void rest_BadRequest_invalidInput() throws Exception {
        RestExceptionThrowerCommand command = new RestExceptionThrowerCommand();
        Mode mode = DemoappModeFactory.unitTest();
        executor.execute(command, mode, new ExceptionThrowerParams(ExceptionThrowerParams.ExceptionType.InvalidInput, 100)).orNull();
    }

    @Test(expectedExceptions=InternalServerErrorServiceException.class)
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
