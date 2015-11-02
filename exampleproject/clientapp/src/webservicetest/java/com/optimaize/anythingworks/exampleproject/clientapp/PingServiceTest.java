package com.optimaize.anythingworks.exampleproject.clientapp;

import com.google.common.base.Stopwatch;
import com.optimaize.command4j.Command;
import com.optimaize.command4j.CommandExecutor;
import com.optimaize.command4j.Mode;
import com.optimaize.command4j.lang.Duration;
import com.optimaize.anythingworks.exampleproject.clientlib.DemoappRemoteExecutors;
import com.optimaize.anythingworks.exampleproject.clientlib.rest.services.system.ping.RestPingCommand;
import com.optimaize.anythingworks.exampleproject.clientlib.soap.services.system.ping.PingCommand;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

/**
 * In order to run these tests, run the Boot class first.
 *
 * @author Fabian Kessler
 */
public class PingServiceTest {

    private final CommandExecutor executor = DemoappRemoteExecutors.get();

    @Test
    public void soap_Ping() throws Exception {
        PingCommand ping = new PingCommand();
        Mode mode = MyModeFactory.debug();
        executor.service().submitAndWait(ping, mode, null, Duration.millis(1000)).get();
        String result = executor.execute(ping, mode, null).get();
        assertEquals(result, "pong");

//        stressTest(ping);
    }

    @Test
    public void rest_Ping() throws Exception {
        RestPingCommand ping = new RestPingCommand();
        Mode mode = MyModeFactory.debug();
        executor.service().submitAndWait(ping, mode, null, Duration.millis(100000)).get();
        String result = executor.execute(ping, mode, null).get();
        assertEquals(result, "pong");

//        stressTest(ping);
    }

    private void stressTest(Command c) throws Exception {
        Mode mode = MyModeFactory.debug();
        Stopwatch time = Stopwatch.createStarted();
        for (int i=0; i<1000; i++) {
            Object result = executor.service().submitAndWait(c, mode, null, Duration.millis(100000)).get();
            assertEquals("pong", result);
        }
        long timeTaken = time.elapsed(TimeUnit.MILLISECONDS);
        System.out.println(timeTaken+"ms");
    }

}
