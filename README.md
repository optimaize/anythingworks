anythingworks (formerly soapworks)
=========

Java Library to Bootstrap Web service client/server development.

It supports both RESTful web services (JAX-RS) and SOAP / WSDL web services (JAX-WS).

For the Client part there are different modules for REST and SOAP so that a web service client does not have 
unwanted dependencies. In other places (server side) some classes specific for one technology are prefixed
with either "Rest" or "Soap".

Other technologies, like JSON-RPC, could be added. Hence the name "anything works".


## Getting started

### Step 1: 

Run the Boot class. Keep it running.

(If you have other stuff running, you can change the defaults of "localhost" and "port 80" in GrizzlyWebServer.)


### Step 2: 

Run requests. For example point your browser at http://localhost/rest/v1/system/ping

The REST wadl is at http://localhost/rest/v1/application.wadl

And one SOAP wsdl is at http://localhost/soap/v1/system/ping?wsdl

There are also tests you can run from client to server. See the PingServiceTest class.


### Step 3:

Party!


## Core Concepts

This library supports you in developing the whole product line from Server web service application to Client 
library. 

We believe that the end user of a client library does not care what technology is used underneath. 
He needs a nice programming interface, with documented methods. Without having to worry about low level 
(HTTP) functionality. 

Also, as history has shown again and again, technologies evolve, new ones arise and older ones fall out of fashion. 
The past has seen more SOAP adoption, now it's REST, and there will be others (HTTP/2, non-HTTP, binary, ...).

If the client code is tightly coupled to the technology then changes will reflect in the user's code. We believe 
that shouldn't be. Therefore this library hides the web service technology as much as possible. 



## Technology stack

* Java 7
* Guava: for the Optional class, and some other things
* JAX-RS: for REST
* JAX-WS: for SOAP
* Spring: for Dependency Injection
* Grizzly: as embedded web server
* Jackson: for JSON marshalling
* Swagger: for generating api documentation and swagger.json at build time, nothing at runtime
* command4j: for command interception
* slf4j: for Logging (Logback)
* testng: for testing


## The modules

### client

This is the dependency to develop a web service client. 

For REST:

    <dependency>
        <groupId>com.optimaize.anythingworks.client.rest</groupId>
        <artifactId>anythingworks-client-rest</artifactId>
        <version>0.5-SNAPSHOT</version>
    </dependency>

For SOAP: 

    <dependency>
        <groupId>com.optimaize.anythingworks.client.soap</groupId>
        <artifactId>anythingworks-client-soap</artifactId>
        <version>0.5-SNAPSHOT</version>
    </dependency>


### server

This is the dependency to develop a web service server.
There are 2 implementations available:

1. implgrizzly using the org.glassfish.grizzly grizzly-http-server dependency (recommended)
2. impljdk which uses the built-in http server from the JDK

If you wish to use another http server, then implement your own module.

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
a long time, and is now published on GitHub. It contains a full working client/server example. 


## The name

Initially this software was for SOAP https://en.wikipedia.org/wiki/SOAP development only. 
The name soapworks is a fun combination of "soap" the technology and "works" because it is a technology that works, 
and the fact that soapworks means soap factory (a place where soaps for washing/bathing/cleaning are produced).

Then REST https://en.wikipedia.org/wiki/Representational_state_transfer support was added as a first class citizen.
Instead of adding rest to the project name, a more generic name was chosen. Other future technologies may work as well.

