package com.optimaize.anythingworks.exampleproject.server.services.soap.system.ping;

import com.google.common.base.Optional;
import com.optimaize.command4j.Command;
import com.optimaize.command4j.ExecutionContext;
import com.optimaize.command4j.commands.BaseCommand;
import com.optimaize.anythingworks.exampleproject.server.lib.BaseWebService;
import com.optimaize.anythingworks.server.soap.SoapWebService;
import com.optimaize.anythingworks.server.soap.exception.AccessDeniedWebServiceException;
import com.optimaize.anythingworks.server.soap.exception.InternalServerErrorWebServiceException;
import com.optimaize.anythingworks.server.soap.exception.InvalidInputWebServiceException;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

/**
 */
@WebService @Service
public class SoapPingService extends BaseWebService implements SoapWebService {

    @WebMethod(exclude=true) @NotNull
    public String getServicePath() {
        return "system/ping";
    }

    @WebMethod
    public String ping(
            @WebParam(name="apiKey") @XmlElement(nillable=false,required=true) final String apiKey
    ) throws AccessDeniedWebServiceException, InvalidInputWebServiceException, InternalServerErrorWebServiceException {
        return execute(new BaseCommand<Object, String>() {
                @Override
                public String call(@NotNull Optional<Object> arg, @NotNull ExecutionContext ec) throws Exception {
                    return "pong";
                }
        }).orNull();
    }

    @NotNull
    protected Optional<String> execute(Command<Object, String> command)
            throws AccessDeniedWebServiceException, InvalidInputWebServiceException, InternalServerErrorWebServiceException {
        return soapExceptionBarrier(command, modeFactory.soapDefaultMode(), null);
    }

}
