package com.optimaize.soapworks.exampleproject.server.boot;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 */
public class Boot {

    /**
     * Or else JDK is used.
     */
    private static final boolean USE_GRIZZLY = true;

    public static void main(String... args) throws Exception {
        Boot boot = new Boot();
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

        SoapWebServer server;
        if (USE_GRIZZLY) {
            server = context.getBean(GrizzlySoapWebServer.class);
        } else {
            server = context.getBean(JdkSoapWebServer.class);
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
