package com.optimaize.soapworks.exampleproject.clientapp;

import com.optimaize.command4j.CommandExecutor;
import com.optimaize.command4j.Mode;
import com.optimaize.command4j.lang.Duration;
import com.optimaize.soapworks.exampleproject.clientlib.lib.DemoappRemoteExecutors;
import com.optimaize.soapworks.exampleproject.clientlib.services.system.ping.PingCommand;
import org.testng.annotations.Test;

/**
 */
public class PingServiceImplTest {

    private final CommandExecutor executor = DemoappRemoteExecutors.get();

    @Test
    public void testPing() throws Exception {
        PingCommand ping = new PingCommand();
        Mode mode = MyModeFactory.debug();
        executor.service().submitAndWait(ping, mode, null, Duration.millis(1000)).get();
        executor.execute(ping, mode, null).get();
    }

}
