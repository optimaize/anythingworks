package com.optimaize.anythingworks.exampleproject.clientlib;

import com.optimaize.anythingworks.client.common.Keys;
import com.optimaize.anythingworks.client.rest.RestKeys;
import com.optimaize.anythingworks.client.soap.SoapKeys;
import com.optimaize.command4j.Mode;
import com.optimaize.command4j.ext.extensions.exception.exceptiontranslation.CombinedExceptionTranslator;
import com.optimaize.command4j.ext.extensions.exception.exceptiontranslation.ExceptionTranslationExtension;
import com.optimaize.command4j.ext.extensions.timeout.configurabletimeout.TimeoutExtension;
import com.optimaize.command4j.lang.Duration;
import com.optimaize.anythingworks.client.soap.exensions.exceptiontranslation.DefaultClientExceptionTranslator;
import com.optimaize.anythingworks.client.soap.exensions.exceptiontranslation.SoapFaultExceptionTranslator;
import com.optimaize.anythingworks.common.host.Host;
import com.optimaize.anythingworks.exampleproject.clientlib.rest.DemoappRestPortUrlFactory;
import com.optimaize.anythingworks.exampleproject.clientlib.soap.DemoappSoapPortUrlFactory;
import org.jetbrains.annotations.NotNull;

/**
 * Provides Mode instances that contain the minimum.
 *
 * @author Fabian Kessler
 */
public class DemoappModeFactory {

    private static final Mode minimalMode = Mode.create()
            .with(SoapKeys.SOAP_PORT_URL_FACTORY, new DemoappSoapPortUrlFactory())
            .with(RestKeys.REST_PORT_URL_FACTORY, new DemoappRestPortUrlFactory())
            .with(ExceptionTranslationExtension.TRANSLATOR, new CombinedExceptionTranslator(new DefaultClientExceptionTranslator(), new SoapFaultExceptionTranslator()))
    ;

    private static final Mode unitTestMode = minimal()
            .with(DemoappKeys.API_KEY, "my-api-key")
            .with(Keys.HOST, new Host("localhost", 80))
            .with(TimeoutExtension.TIMEOUT, Duration.millis(5000))
            //.with(StdoutLoggingExtension.enabled())
    ;



    @NotNull
    public static Mode minimal() {
        return minimalMode;
    }

    @NotNull
    public static Mode unitTest() {
        return unitTestMode;
    }

}
