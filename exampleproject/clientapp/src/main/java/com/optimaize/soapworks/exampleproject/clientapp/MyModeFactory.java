package com.optimaize.soapworks.exampleproject.clientapp;

import com.optimaize.command4j.Mode;
import com.optimaize.command4j.ext.extensions.failover.autoretry.AutoRetryExtension;
import com.optimaize.command4j.ext.extensions.logging.stdoutlogging.StdoutLoggingExtension;
import com.optimaize.command4j.ext.extensions.timeout.configurabletimeout.TimeoutExtension;
import com.optimaize.command4j.lang.Duration;
import com.optimaize.soapworks.client.Keys;
import com.optimaize.soapworks.client.soap.exensions.autoretry.DefaultAutoRetryStrategy;
import com.optimaize.soapworks.common.host.Host;
import com.optimaize.soapworks.exampleproject.clientlib.DemoappKeys;
import com.optimaize.soapworks.exampleproject.clientlib.DemoappModeFactory;
import org.jetbrains.annotations.NotNull;

/**
 * Provides Mode instances that contain the minimum for this demoapp.
 *
 * @author Fabian Kessler
 */
public class MyModeFactory {

    private static final Mode minimalMode = DemoappModeFactory.minimal()
            .with(DemoappKeys.API_KEY, "my-api-key")
            .with(Keys.HOST, new Host("localhost", 80))
    ;

    private static final Mode commonMode = minimalMode
            .with(TimeoutExtension.TIMEOUT, Duration.millis(5000))
            .with(AutoRetryExtension.STRATEGY, new DefaultAutoRetryStrategy())
    ;

    private static final Mode debugMode = minimalMode
            .with(TimeoutExtension.TIMEOUT, Duration.millis(5000))
            .with(StdoutLoggingExtension.enabled())
    ;


    @NotNull
    public static Mode minimal() {
        return minimalMode;
    }

    @NotNull
    public static Mode common() {
        return commonMode;
    }

    @NotNull
    public static Mode debug() {
        return debugMode;
    }

}
