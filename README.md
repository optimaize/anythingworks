soapworks
=========

Java Library to Bootstrap SOAP / WSDL Development.

## The modules

### client

This is the dependency to develop a SOAP client.

    <dependency>
        <groupId>com.optimaize.soapworks.client</groupId>
        <artifactId>soapworks-client</artifactId>
        <version>0.2</version>
    </dependency>

### server

This is the dependency to develop a SOAP server.
There are 2 implementations available:

1. implgrizzly using the org.glassfish.grizzly grizzly-http-server dependency (recommended)
2. impljdk which uses the built-in http server from the JDK

If you wish to use another http server, then implement your own module. There's only one
interface to implement (SoapWebServerHandler).

    <dependency>
        <groupId>com.optimaize.soapworks.server.implgrizzly</groupId>
        <artifactId>soapworks-server-implgrizzly</artifactId>
        <version>0.2</version>
    </dependency>

### common

Both client and server depend on this and include it already. Shared code is in here.

### exampleproject

This project is a full working example containing a runnable server,
and a client that connects to it.

It is not needed in your production environment.


Exception handling
------------------
Exceptions are named by who's to blame, not who threw the exception.
A server can throw a ClientException just like in http a server
can return a code 400 (client error).


Status
------
soapworks has been in development at http://www.optimaize.com for use in the http://www.nameapi.org web services for a long time, and is now published on GitHub. It contains a full working example. This readme should be longer.
