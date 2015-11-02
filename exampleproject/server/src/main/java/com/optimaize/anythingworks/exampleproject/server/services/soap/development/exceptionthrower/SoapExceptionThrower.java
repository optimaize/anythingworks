package com.optimaize.anythingworks.exampleproject.server.services.soap.development.exceptionthrower;

import com.optimaize.anythingworks.exampleproject.server.lib.BaseWebService;
import com.optimaize.anythingworks.server.soap.SoapWebService;
import com.optimaize.anythingworks.server.soap.exception.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

/**
 */
@WebService @Service
public class SoapExceptionThrower extends BaseWebService implements SoapWebService {

    @WebMethod(exclude=true) @NotNull
    public String getServicePath() {
        return "system/exceptionthrower";
    }


    @WebMethod
    public String throwAccessDeniedNoSuchAccount(
            @WebParam(name="apiKey") @XmlElement(nillable=false,required=true) final String apiKey
    )
            throws AccessDeniedWebServiceException, InvalidInputWebServiceException, InternalServerErrorWebServiceException
    {
        throw AccessDeniedWebServiceException.accountUnknown(apiKey);
    }

    @WebMethod
    public String throwAccessDeniedRequestLimitExceeded(
            @WebParam(name="apiKey") @XmlElement(nillable=false,required=true) final String apiKey
    )
            throws AccessDeniedWebServiceException, InvalidInputWebServiceException, InternalServerErrorWebServiceException
    {
        throw AccessDeniedWebServiceException.requestLimitExceeded(apiKey, "unspecified");
    }

    @WebMethod
    public String throwAccessDeniedTooManyConcurrentRequests(
            @WebParam(name="apiKey") @XmlElement(nillable=false,required=true) final String apiKey
    )
            throws AccessDeniedWebServiceException, InvalidInputWebServiceException, InternalServerErrorWebServiceException
    {
        throw AccessDeniedWebServiceException.tooManyConcurrentRequests(apiKey);
    }

    @WebMethod
    public String throwInvalidInput(
            @WebParam(name="apiKey") @XmlElement(nillable=false,required=true) final String apiKey
    )
            throws AccessDeniedWebServiceException, InvalidInputWebServiceException, InternalServerErrorWebServiceException
    {
        throw new InvalidInputWebServiceException("Throwing on demand!");
    }

    @WebMethod
    public String throwInternal(
            @WebParam(name="apiKey") @XmlElement(nillable=false,required=true) final String apiKey
    )
            throws AccessDeniedWebServiceException, InvalidInputWebServiceException, InternalServerErrorWebServiceException
    {
        throw new InternalServerErrorWebServiceException(
                FaultBeanBuilders.Server.internalServerError()
                        .errorCode(ErrorCodes.Server.SERVER_ERROR.getCode())
                        .message("Throwing on demand!")
                        .retrySameServer(Retry.unknown())
                        .retryOtherServers(Retry.unknown())
                        .problemReported(false) //whatever
        );
    }

}
