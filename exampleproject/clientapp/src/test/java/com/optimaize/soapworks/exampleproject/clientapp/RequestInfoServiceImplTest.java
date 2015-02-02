package com.optimaize.soapworks.exampleproject.clientapp;

import com.optimaize.command4j.CommandExecutor;
import com.optimaize.command4j.Mode;
import com.optimaize.soapworks.exampleproject.clientlib.lib.DemoappRemoteExecutors;
import com.optimaize.soapworks.exampleproject.clientlib.services.system.requestinfo.RequestInfoCommand;
import org.testng.annotations.Test;

/**
 */
public class RequestInfoServiceImplTest {

    private final CommandExecutor executor = DemoappRemoteExecutors.get();

    //In order to run this test, run the Boot class first.
//    @Test
    public void testRequestInfo() throws Exception {
        RequestInfoCommand cmd = new RequestInfoCommand();
        Mode mode = MyModeFactory.debug();
        String info = executor.execute(cmd, mode, null).get();
        System.out.println(info);
    }

}
