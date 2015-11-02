anythingworks (formerly soapworks)
=========

Java Library to Bootstrap Web service client/server development.

It supports both RESTful web services (JAX-RS) and
SOAP / WSDL web services (JAX-WS).

Within this project classes specific for one technology are prefixed
with either "Rest" or "Soap".

Other technologies, like JSON-RPC, could be added. Hence the name "anything works".


## The modules

### client

This is the dependency to develop a web service client.

    <dependency>
        <groupId>com.optimaize.anythingworks.client</groupId>
        <artifactId>anythingworks-client</artifactId>
        <version>0.5-SNAPSHOT</version>
    </dependency>

### server

This is the dependency to develop a web service server.
There are 2 implementations available:

1. implgrizzly using the org.glassfish.grizzly grizzly-http-server dependency (recommended)
2. impljdk which uses the built-in http server from the JDK

If you wish to use another http server, then implement your own module. There's only one
interface to implement (SoapWebServerHandler).

    <dependency>
        <groupId>com.optimaize.anythingworks.server.implgrizzly</groupId>
        <artifactId>anythingworks-server-implgrizzly</artifactId>
        <version>0.5-SNAPSHOT</version>
    </dependency>

### common

Both client and server depend on this and include it already. Shared code is in here.

### exampleproject

This project is a full working example containing a runnable server, and a client that connects to it. 
It is not needed in your production environment.

To try it out, start the **Boot** class. Then you can hit example URLs in your browser, one 
is http://localhost/rest/v1/development/get and you can run Test classes, for example 
com.optimaize.anythingworks.exampleproject.clientapp.PingServiceTest

The project shows you how to do a few common tasks:

* POJO mappings with immutable classes. It uses Jackson. See ComplexObject.class
* Polymorphic types, see GeometricalFigure.class
* Sharing code by client and server, using the ontology module.
* Exception barrier and translation. 
* Optional REST envelope. Add ?envelope=true to the url.


## Features

### Integrated server

The project has code for starting an integrated web server (Grizzly) and for automatically 
registering the REST and/or SOAP services in it.

### Command interception

You can do automatic retries, or request/response logging, and other cross cutting concern. 
The project uses Commands from the https://github.com/optimaize/command4j library to define 
web service actions. Whether the commands use REST or SOAP is irrelevant to the programmer.


## Status

anythingworks (soapworks) has been in development at http://www.optimaize.com for use in the http://www.nameapi.org web services for 
a long time, and is now published on GitHub. It contains a full working example. This readme should be longer.
See the wiki https://github.com/optimaize/anythingworks/wiki


## The name

Initially this software was for SOAP https://en.wikipedia.org/wiki/SOAP development only. 
The name soapworks is a fun combination of "soap" the technology and "works" because it is a technology that works, 
and the fact that soapworks means soap factory (a place where soaps for washing/bathing/cleaning are produced).

Then REST https://en.wikipedia.org/wiki/Representational_state_transfer support was added as a first class citizen.
Instead of adding rest to the project name, a more generic name was chosen. Other future technologies may work as well.

