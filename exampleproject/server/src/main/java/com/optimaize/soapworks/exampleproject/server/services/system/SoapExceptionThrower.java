package com.optimaize.soapworks.exampleproject.server.services.system;

import com.optimaize.soapworks.exampleproject.server.lib.AbstractSoapWebService;
import com.optimaize.soapworks.server.exception.AccessDeniedWebServiceException;
import com.optimaize.soapworks.server.exception.InternalServerErrorWebServiceException;
import com.optimaize.soapworks.server.exception.InvalidInputWebServiceException;
import com.optimaize.soapworks.server.exception.Retry;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

/**
 */
@WebService @Service
public class SoapExceptionThrower extends AbstractSoapWebService {

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
        throw AccessDeniedWebServiceException.noSuchAccount(apiKey);
    }

    @WebMethod
    public String throwAccessDeniedRequestLimitExceeded(
            @WebParam(name="apiKey") @XmlElement(nillable=false,required=true) final String apiKey
    )
            throws AccessDeniedWebServiceException, InvalidInputWebServiceException, InternalServerErrorWebServiceException
    {
        throw AccessDeniedWebServiceException.requestLimitExceeded();
    }

    @WebMethod
    public String throwAccessDeniedTooManyConcurrentRequests(
            @WebParam(name="apiKey") @XmlElement(nillable=false,required=true) final String apiKey
    )
            throws AccessDeniedWebServiceException, InvalidInputWebServiceException, InternalServerErrorWebServiceException
    {
        throw AccessDeniedWebServiceException.tooManyConcurrentRequests();
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
        throw new InternalServerErrorWebServiceException(Retry.unknown(), false);
    }

}
