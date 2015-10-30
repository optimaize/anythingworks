package com.optimaize.soapworks.exampleproject.clientapp;

import com.google.common.base.Stopwatch;
import com.optimaize.command4j.Command;
import com.optimaize.command4j.CommandExecutor;
import com.optimaize.command4j.Mode;
import com.optimaize.command4j.lang.Duration;
import com.optimaize.soapworks.exampleproject.clientlib.DemoappRemoteExecutors;
import com.optimaize.soapworks.exampleproject.ontology.rest.development.post.Circle;
import com.optimaize.soapworks.exampleproject.ontology.rest.development.post.ComplexObject;
import com.optimaize.soapworks.exampleproject.clientlib.rest.services.development.post.RestPostCommand;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;

/**
 * In order to run these tests, run the Boot class first.
 *
 * @author Fabian Kessler
 */
public class PostServiceTest {

    private final CommandExecutor executor = DemoappRemoteExecutors.get();

    @Test
    public void rest_Post() throws Exception {
        RestPostCommand ping = new RestPostCommand();
        Mode mode = MyModeFactory.debug();
        ComplexObject param = new ComplexObject("nana", 42, true, ComplexObject.Color.RED, new Circle("blue", 5d));
        ComplexObject result = executor.service().submitAndWait(ping, mode, param, Duration.millis(100000)).get();
        assertEquals(result.getString(), param.getString()+"-result");
        assertEquals(result.getNumber(), param.getNumber()*2);
        assertEquals(result.isYesOrNo(), !param.isYesOrNo());
        assertEquals(result.getColor(), ComplexObject.Color.RED);
        assertEquals(result.getGeometricalFigure().getColor(), "light"+param.getGeometricalFigure().getColor());
        assertTrue(result.getGeometricalFigure() instanceof Circle);

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
