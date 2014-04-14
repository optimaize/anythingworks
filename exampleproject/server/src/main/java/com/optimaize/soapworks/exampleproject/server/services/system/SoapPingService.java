package com.optimaize.soapworks.exampleproject.server.services.system;

import com.google.common.base.Optional;
import com.optimaize.command4j.Command;
import com.optimaize.command4j.ExecutionContext;
import com.optimaize.command4j.commands.BaseCommand;
import com.optimaize.soapworks.exampleproject.server.lib.AbstractSoapWebService;
import com.optimaize.soapworks.exception.AccessDeniedWebServiceException;
import com.optimaize.soapworks.exception.InternalServerErrorWebServiceException;
import com.optimaize.soapworks.exception.InvalidInputWebServiceException;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

/**
 */
@WebService @Service
public class SoapPingService extends AbstractSoapWebService {

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
        return exceptionBarrier(command, modeFactory.defaultMode(), null);
    }

}
