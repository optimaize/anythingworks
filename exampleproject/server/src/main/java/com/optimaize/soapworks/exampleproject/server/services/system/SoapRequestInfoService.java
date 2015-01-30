package com.optimaize.soapworks.exampleproject.server.services.system;

import com.google.common.base.Optional;
import com.optimaize.command4j.Command;
import com.optimaize.command4j.ExecutionContext;
import com.optimaize.command4j.commands.BaseCommand;
import com.optimaize.soapworks.exampleproject.server.lib.AbstractSoapWebService;
import com.optimaize.soapworks.server.exception.AccessDeniedWebServiceException;
import com.optimaize.soapworks.server.exception.InternalServerErrorWebServiceException;
import com.optimaize.soapworks.server.exception.InvalidInputWebServiceException;
import com.sun.xml.ws.server.AbstractWebServiceContext;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import java.util.Map;

/**
 */
@WebService @Service
public class SoapRequestInfoService extends AbstractSoapWebService {

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
//        java.lang.IllegalStateException: getMessageContext() can only be called while servicing a request
        final MessageContext messageContext = webServiceContext.getMessageContext();
        return makeMsg(webServiceContext, messageContext);


//        return execute(new BaseCommand<Object, String>() {
//                @Override
//                public String call(@NotNull Optional<Object> arg, @NotNull ExecutionContext ec) throws Exception {
//                    return makeMsg(messageContext);
//                }
//        }).orNull();
    }

    private String makeMsg(WebServiceContext webServiceContext, MessageContext messageContext) {
        StringBuilder sb = new StringBuilder();
        sb.append("Request method: "+messageContext.get(MessageContext.HTTP_REQUEST_METHOD));
        sb.append(", Path: "+messageContext.get(MessageContext.PATH_INFO));
        sb.append(", Query string: "+messageContext.get(MessageContext.QUERY_STRING));
        sb.append(", User agent: "+((Map)messageContext.get(MessageContext.HTTP_REQUEST_HEADERS)).get("user-agent"));
        sb.append(", Content type: "+((Map)messageContext.get(MessageContext.HTTP_REQUEST_HEADERS)).get("content-type"));
        sb.append(", Http accept: "+((Map)messageContext.get(MessageContext.HTTP_REQUEST_HEADERS)).get("accept"));
        sb.append(", Http host: "+((Map)messageContext.get(MessageContext.HTTP_REQUEST_HEADERS)).get("host"));
        sb.append(", Connection: "+((Map)messageContext.get(MessageContext.HTTP_REQUEST_HEADERS)).get("connection"));
        sb.append(", wasTransportSecure: "+((AbstractWebServiceContext) webServiceContext).getRequestPacket().wasTransportSecure);
        return sb.toString();
    }

    @NotNull
    protected Optional<String> execute(Command<Object, String> command)
            throws AccessDeniedWebServiceException, InvalidInputWebServiceException, InternalServerErrorWebServiceException {
        return exceptionBarrier(command, modeFactory.defaultMode(), null);
    }

}
