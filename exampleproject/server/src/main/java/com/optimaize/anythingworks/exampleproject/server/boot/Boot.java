package com.optimaize.anythingworks.exampleproject.server.boot;

import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.logging.LogManager;

/**
 */
public class Boot {

    /**
     * Or else JDK is used.
     */
    private static final boolean USE_GRIZZLY = true;

    public static void main(String... args) throws Exception {
        Boot boot = new Boot();

        LogManager.getLogManager().reset();
        SLF4JBridgeHandler.install();

        try {
            boot.boot();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        //in case of an exception we don't arrive here because of system.exit!
        System.out.println("Stopping normally.");
        System.exit(0);
    }


    private void boot() throws Exception {
        ConfigurableApplicationContext context = makeAppContext();

        WebServer server;
        if (USE_GRIZZLY) {
            server = context.getBean(GrizzlyWebServer.class);
        } else {
            server = context.getBean(JdkWebServer.class);
        }
        server.start();

        System.out.println("Server ready. Hit enter in the console to stop app.");
        System.in.read();
    }

    private ConfigurableApplicationContext makeAppContext() {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext(
                "spring-boot.xml"
        );
        context.registerShutdownHook();
        return context;
    }

}
