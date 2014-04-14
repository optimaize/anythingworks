package com.optimaize.soapworks.exampleproject.clientapp;

import com.optimaize.command4j.CommandExecutor;
import com.optimaize.command4j.Mode;
import com.optimaize.soapworks.client.exception.AccessDeniedServiceException;
import com.optimaize.soapworks.exampleproject.clientlib.lib.DemoappModeFactory;
import com.optimaize.soapworks.exampleproject.clientlib.lib.DemoappRemoteExecutors;
import com.optimaize.soapworks.exampleproject.clientlib.services.system.exceptionthrower.ExceptionThrowerAccessDeniedNoSuchAccount;
import org.testng.annotations.Test;

/**
 * @author Fabian Kessler
 */
public class ExceptionThrowerTest {

    private final CommandExecutor executor = DemoappRemoteExecutors.get();

    @Test(expectedExceptions=AccessDeniedServiceException.class)
    public void accessDeniedNoSuchAccount() throws Exception {
        ExceptionThrowerAccessDeniedNoSuchAccount command = new ExceptionThrowerAccessDeniedNoSuchAccount();
        Mode mode = DemoappModeFactory.unitTest();
        executor.execute(command, mode, null).orNull();
    }

}
