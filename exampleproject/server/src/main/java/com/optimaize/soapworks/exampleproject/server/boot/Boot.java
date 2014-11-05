package com.optimaize.soapworks.exampleproject.server.boot;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 */
public class Boot {

    public static void main(String... args) throws IOException {
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


    private void boot() throws IOException {
        ConfigurableApplicationContext context = makeAppContext();

        SoapWebServer server = context.getBean(SoapWebServer.class);
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
