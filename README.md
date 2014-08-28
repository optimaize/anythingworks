soapworks
=========

Java Library to Bootstrap SOAP / WSDL Development.

## The modules

### client

This is the dependency to develop a SOAP client.

### server

This is the dependency to develop a SOAP server.

### common

Both client and server depend on this. Shared code is in here.

### exampleproject

This project is a full working example containing a runnable server,
and a client that connects to it.

It is not needed in your production environment, thus it is not available as a Maven artifact.


Exception handling
------------------
Exceptions are named by who's to blame, not who threw the exception.
A server can throw a ClientException just like in http a server
can return a code 400 (client error).


Status
------
soapworks has been in development at http://www.optimaize.com for use in the http://www.nameapi.org web services for a long time, and is now published on GitHub. It contains a full working example. This readme should be longer.
