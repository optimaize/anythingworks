package com.optimaize.soapworks.exampleproject.server.services.soap.development.requestinfo;

import com.google.common.base.Optional;
import com.optimaize.command4j.Command;
import com.optimaize.command4j.ExecutionContext;
import com.optimaize.command4j.commands.BaseCommand;
import com.optimaize.soapworks.exampleproject.server.lib.BaseWebService;
import com.optimaize.soapworks.server.soap.SoapWebService;
import com.optimaize.soapworks.server.soap.exception.AccessDeniedWebServiceException;
import com.optimaize.soapworks.server.soap.exception.InternalServerErrorWebServiceException;
import com.optimaize.soapworks.server.soap.exception.InvalidInputWebServiceException;
import com.optimaize.soapworks.server.implcommon.soap.EagerRequestDataExtractor;
import com.optimaize.soapworks.server.implcommon.soap.RequestDataExtractor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.ws.WebServiceContext;

/**
 */
@WebService @Service
public class SoapRequestInfoService extends BaseWebService implements SoapWebService {

    @Resource //not @Inject, won't work!!!
    private WebServiceContext webServiceContext;

    @WebMethod(exclude=true) @NotNull
    public String getServicePath() {
        return "system/requestinfo";
    }

    @WebMethod
    public String requestInfo(
            @WebParam(name="apiKey") @XmlElement(nillable=false,required=true) final String apiKey
    ) throws AccessDeniedWebServiceException, InvalidInputWebServiceException, InternalServerErrorWebServiceException {
        //because only this thread has access to the request, and the command4j framework uses another thread for
        //the execution of the call, we must use the eager impl here:
        final EagerRequestDataExtractor extractor = new EagerRequestDataExtractor(webServiceContext);

        return execute(new BaseCommand<Object, String>() {
                @Override
                public String call(@NotNull Optional<Object> arg, @NotNull ExecutionContext ec) throws Exception {
                    return makeMsg(extractor);
                }
        }).orNull();
    }

    private String makeMsg(RequestDataExtractor extractor) {
        StringBuilder sb = new StringBuilder();
        sb.append("Request method: " + extractor.requestMethod());
        sb.append(", URI: " + extractor.uri());
        sb.append(", User agent: "+extractor.requestHeaderUserAgent());
        sb.append(", Content type: " + extractor.requestHeaderContentType());
        sb.append(", Http accept: "+extractor.requestHeaderAccept());
        sb.append(", Http host: " + extractor.requestHeaderHost());
        sb.append(", Connection: "+extractor.requestHeaderConnection());
        sb.append(", wasTransportSecure: " + extractor.wasTransportSecure());
        return sb.toString();
    }

    @NotNull
    protected Optional<String> execute(Command<Object, String> command)
            throws AccessDeniedWebServiceException, InvalidInputWebServiceException, InternalServerErrorWebServiceException {
        return exceptionBarrier(command, modeFactory.soapDefaultMode(), null);
    }

}
