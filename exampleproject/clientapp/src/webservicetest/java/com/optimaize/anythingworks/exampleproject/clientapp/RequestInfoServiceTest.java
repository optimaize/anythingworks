package com.optimaize.anythingworks.exampleproject.clientapp;

import com.optimaize.command4j.CommandExecutor;
import com.optimaize.command4j.Mode;
import com.optimaize.anythingworks.exampleproject.clientlib.DemoappRemoteExecutors;
import com.optimaize.anythingworks.exampleproject.clientlib.rest.services.development.requestinfo.RequestInfo;
import com.optimaize.anythingworks.exampleproject.clientlib.rest.services.development.requestinfo.RestRequestInfoCommand;
import com.optimaize.anythingworks.exampleproject.clientlib.soap.services.development.requestinfo.RequestInfoCommand;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * In order to run these tests, run the Boot class first.
 *
 * @author Fabian Kessler
 */
public class RequestInfoServiceTest {

    private final CommandExecutor executor = DemoappRemoteExecutors.get();

    @Test
    public void soap_RequestInfo() throws Exception {
        RequestInfoCommand cmd = new RequestInfoCommand();
        Mode mode = MyModeFactory.debug();
        String info = executor.execute(cmd, mode, null).get();
        System.out.println(info);
    }

    @Test
    public void rest_RequestInfo() throws Exception {
        RestRequestInfoCommand cmd = new RestRequestInfoCommand();
        Mode mode = MyModeFactory.debug();
        RequestInfo info = executor.execute(cmd, mode, null).get();
        assertEquals(info.getMethod(), "GET");
        assertEquals(info.getUserAgent(), "Java-Client");
        System.out.println(info);
    }

}
