package com.optimaize.anythingworks.exampleproject.server.services.soap.development.exceptionthrower;

import com.google.common.base.Optional;
import com.optimaize.anythingworks.exampleproject.server.lib.BaseWebService;
import com.optimaize.anythingworks.exampleproject.server.services.impl.ExceptionThrower;
import com.optimaize.anythingworks.server.soap.SoapWebService;
import com.optimaize.anythingworks.server.soap.exception.SoapWebServiceException;
import com.optimaize.command4j.Command;
import com.optimaize.command4j.ExecutionContext;
import com.optimaize.command4j.commands.BaseCommand;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

/**
 */
@WebService @Service
public class SoapExceptionThrower extends BaseWebService implements SoapWebService {

    @Resource
    private ExceptionThrower exceptionThrower;


    @WebMethod(exclude=true) @NotNull
    public String getServicePath() {
        return "development/exceptionthrower";
    }



    @WebMethod
    public String throwException(
            @WebParam(name="apiKey") @XmlElement(nillable=false,required=true) final String apiKey,
            @WebParam(name="exceptionType") @XmlElement(nillable=false,required=true) final String exceptionType,
            @WebParam(name="exceptionChance") @XmlElement(nillable=false,required=true) final int exceptionChance
    ) throws SoapWebServiceException {
        return execute(new BaseCommand<Object, String>() {
            @Override
            public String call(@NotNull Optional<Object> arg, @NotNull ExecutionContext ec) throws Exception {
                return exceptionThrower.throwException(apiKey, exceptionType, exceptionChance);
            }
        }).orNull();
    }


    @NotNull
    protected Optional<String> execute(Command<Object, String> command) throws SoapWebServiceException {
        return soapExceptionBarrier(command, modeFactory.soapDefaultMode(), null);
    }

}
