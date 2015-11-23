package com.optimaize.anythingworks.client.rest.http;

import com.google.common.base.Optional;
import com.optimaize.anythingworks.client.rest.json.ClientJacksonJsonMarshallerFactory;
import com.optimaize.anythingworks.common.fault.ErrorCodes;
import com.optimaize.anythingworks.common.fault.exceptions.*;
import com.optimaize.anythingworks.common.fault.faultinfo.Blame;
import com.optimaize.anythingworks.common.fault.faultinfo.Retry;
import com.optimaize.anythingworks.common.rest.JsonMarshaller;
import com.optimaize.anythingworks.common.rest.JsonMarshallingException;
import com.optimaize.anythingworks.common.rest.TypeRef;
import com.optimaize.anythingworks.common.rest.fault.RestFaultInfo;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Thread safe.
 *
 * This code is based on generated code from Swagger Codegen.
 * It's refactored a bit, but still feels awkward.
 */
public class RestHttpClientImpl implements RestHttpClient {

    /**
     * For now it's all JSON, no XML.
     * Add methods to pass these values in if you need it.
     */
    private static final String ACCEPT = "application/json";
    /**
     * As with ACCEPT, for now it's all JSON no XML.
     *
     * It really shouldn't be necessary to define the charset here. If you need it, the correct value is:
     * CONTENT_TYPE = "application/json; charset=UTF-8"
     * See http://javaspree.com/2014/08/java-jersey-client-setting-character-encoding/ as an example where
     * someone needed that. By definition, all JSON is UTF.
     *
     */
    private static final String CONTENT_TYPE = "application/json";


    /**
     * Creation of these Client instances is expensive, and according to its Javadoc, they
     * should be shared.
     */
    private static ConcurrentMap<String, Client> hostMap = new ConcurrentHashMap<>();


    /**
     * Builder for creating a {@link RestHttpClientImpl}.
     */
    public static class Builder {
        private String basePath;
        private HeaderParams defaultHeaders = HeaderParams.create();
        private boolean debug = false;

        private JsonMarshaller jsonMarshaller = ClientJacksonJsonMarshallerFactory.create();
        private String jsonEnvelopeResultAttributeName = null;
        private String jsonEnvelopeErrorAttributeName = null;

        private DateFormat dateFormat;

        public Builder basePath(String basePath) {
            this.basePath = basePath;
            return this;
        }
        public Builder defaultHeader(String key, String value) {
            defaultHeaders.put(key, value);
            return this;
        }
        /**
         * Set the User-Agent header's value (by adding to the default header map).
         */
        public Builder userAgent(String userAgent) {
            defaultHeaders.userAgent(userAgent);
            return this;
        }
        public Builder debug(boolean debug) {
            this.debug = debug;
            return this;
        }
        public Builder json(JsonMarshaller jsonMarshaller) {
            this.jsonMarshaller = jsonMarshaller;
            return this;
        }
        public Builder json(JsonMarshaller jsonMarshaller,
                            String jsonEnvelopeResultAttributeName,
                            String jsonEnvelopeErrorAttributeName) {
            this.jsonMarshaller = jsonMarshaller;
            this.jsonEnvelopeResultAttributeName = jsonEnvelopeResultAttributeName;
            this.jsonEnvelopeErrorAttributeName = jsonEnvelopeErrorAttributeName;
            return this;
        }
        public Builder dateFormat(DateFormat dateFormat) {
            this.dateFormat = dateFormat;
            return this;
        }
        public RestHttpClient build() {
            if (dateFormat==null) {
                // Use ISO 8601 format for date and datetime.
                // See https://en.wikipedia.org/wiki/ISO_8601
                dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
                // Use UTC as the default time zone.
                dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            }

            return new RestHttpClientImpl(
                    basePath,
                    defaultHeaders,
                    debug,
                    jsonMarshaller,
                    jsonEnvelopeResultAttributeName,
                    jsonEnvelopeErrorAttributeName,
                    dateFormat
            );
        }
    }


    private final String basePath;
    private final HeaderParams defaultHeaders;
    private final boolean debug;

    private final JsonMarshaller jsonMarshaller;
    private String jsonEnvelopeResultAttributeName = null;
    private String jsonEnvelopeErrorAttributeName = null;

    private final DateFormat dateFormat;


    private RestHttpClientImpl(String basePath, HeaderParams defaultHeaders, boolean debug,
                               JsonMarshaller jsonMarshaller,
                               String jsonEnvelopeResultAttributeName,
                               String jsonEnvelopeErrorAttributeName,
                               DateFormat dateFormat) {
        this.basePath = basePath;
        this.defaultHeaders = defaultHeaders;
        this.debug = debug;
        this.jsonMarshaller = jsonMarshaller;
        this.jsonEnvelopeResultAttributeName = jsonEnvelopeResultAttributeName;
        this.jsonEnvelopeErrorAttributeName = jsonEnvelopeErrorAttributeName;
        this.dateFormat = dateFormat;
    }


    /**
     * Format the given Date object into string.
     */
    private String formatDate(Date date) {
        return dateFormat.format(date);
    }

    /**
     * Format the given parameter object into string.
     */
    private String parameterToString(Object param) {
        if (param == null) {
            return "";
        } else if (param instanceof Date) {
            return formatDate((Date) param);
        } else if (param instanceof Collection) {
            StringBuilder b = new StringBuilder();
            for (Object o : (Collection) param) {
                if (b.length() > 0) {
                    b.append(",");
                }
                b.append(String.valueOf(o));
            }
            return b.toString();
        } else {
            return String.valueOf(param);
        }
    }

    /**
     * Escape the given string to be used as URL query value.
     */
    private String escapeString(String str) {
        try {
            return URLEncoder.encode(str, "utf8").replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException e) {
            return str;
        }
    }

    /**
     * Serialize the given Java object into string entity according the given
     * Content-Type (only JSON is supported for now).
     * This data is then sent to the server.
     */
    private Entity<String> serialize(Object obj, String contentType) throws ClientErrorException {
        if (contentType.startsWith("application/json")) {
            try {
                return Entity.json(jsonMarshaller.serialize(obj));
            } catch (JsonMarshallingException e) {
                String msg = "Failed marshalling object to JSON data for sending to server: "+e.getMessage();
//                throw new ClientErrorException(Response.Status.BAD_REQUEST, e);
                throw new BadRequestServiceException(
                        new RestFaultInfo("BadRequest", Blame.CLIENT,
                                msg, ""+ ErrorCodes.Client.MARSHALLING_FAILED.getCode(),
                                null,
                                Retry.no(), Retry.no(),
                                Response.Status.BAD_REQUEST.getStatusCode(),
                                Response.Status.BAD_REQUEST.getReasonPhrase()
                        ), e
                );
            }
        } else {
            String msg = "Content type \"" + contentType + "\" is not supported.";
//            throw new NotSupportedException(msg);
            throw new BadRequestServiceException(
                    new RestFaultInfo("BadRequest", Blame.CLIENT,
                            msg, ""+ ErrorCodes.Client.UNSUPPORTED_TECHNOLOGY.getCode(),
                            null,
                            Retry.no(), Retry.no(),
                            Response.Status.BAD_REQUEST.getStatusCode(),
                            Response.Status.BAD_REQUEST.getReasonPhrase()
                    )
            );
        }
    }

    /**
     * Deserialize response body as received from the server to Java object according to the Content-Type
     * (only JSON is supported for now).
     */
    private <T> T deserializeResult(Response response, @Nullable String envelopeFieldName, TypeRef returnType) throws WebApplicationException {
        String contentType = readContentType(response);
        String body = readBody(response);

        if (contentType.startsWith("application/json")) {
            try {
                if (envelopeFieldName!=null) {
                    return jsonMarshaller.deserializeEnveloped(body, envelopeFieldName, returnType);
                } else {
                    return jsonMarshaller.deserialize(body, returnType);
                }
            } catch (JsonMarshallingException e) {
                String msg = "Failed unmarshalling JSON data from server to object: "+e.getMessage();
//                throw new ClientErrorException(Response.Status.BAD_REQUEST, e);

                Retry retry;
                if (response.getStatusInfo().getFamily() == Response.Status.Family.SERVER_ERROR) {
                    //it is likely that the server can handle it next time, and there won't be an error at all.
                    //then there won't be a problem unmarshalling the error info either.
                    retry = Retry.now();
                } else {
                    //success:      no, because it's unlikely the server sends a different response next time.
                    //client error: no, because the client's data is wrong anyway, we just can't understand the detail
                    //              why it's wrong, but it's still wrong next time.
                    //              this could happen when the client uses an outdated protocol. server rejects it, and
                    //              the client can't even understand (the detail of) the response.
                    retry = Retry.no();
                }
                throw new ClientServiceException(
                        new RestFaultInfo(
                                "Protocol", Blame.CLIENT,
                                msg, ""+ErrorCodes.Client.UNMARSHALLING_FAILED.getCode(),
                                null,
                                retry, retry,
                                response.getStatusInfo().getStatusCode(),
                                response.getStatusInfo().getReasonPhrase()
                        ), e
                );
            }
        } else if (returnType.getType().equals(String.class)) {
            // Expecting string, return the raw response body.
            return (T) body;
        } else {
            String msg = "Client requested content type >>>"+ACCEPT+"<<< but server sent >>>\"" + contentType + "\">>> and that is not supported for type: " + returnType.getType();
//            throw new NotSupportedException(msg);
            throw new BadResponseServiceException(
                    new RestFaultInfo("BadResponse", Blame.SERVER,
                            msg, ""+ErrorCodes.Server.BAD_RESPONSE.getCode(),
                            null,
                            Retry.no(), Retry.no(),
                            response.getStatusInfo().getStatusCode(),
                            response.getStatusInfo().getReasonPhrase()
                    )
            );
        }
    }

    private String readBody(Response response) {
        String body;
        if (response.hasEntity())
            body = (String) response.readEntity(String.class);
        else
            body = "";
        return body;
    }

    @NotNull
    private String readContentType(Response response) throws WebApplicationException {
        String contentType = null;
        List<Object> contentTypes = response.getHeaders().get("Content-Type");
        if (contentTypes != null && !contentTypes.isEmpty()) {
            contentType = String.valueOf(contentTypes.get(0));
        }
        if (contentType == null) {
            String msg = "Missing Content-Type in response";
            //throw new WebApplicationException(msg, 500);
            throw new BadResponseServiceException(
                    new RestFaultInfo("BadResponse", Blame.SERVER,
                            msg, ""+ErrorCodes.Server.BAD_RESPONSE.getCode(),
                            null,
                            Retry.no(), Retry.no(),
                            Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(),
                            Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase()
                    )
            );
        }
        return contentType;
    }

    private String makeQueryString(List<Pair> queryParams) {
        if (queryParams == null || queryParams.isEmpty()) {
            return "";
        }

        StringBuilder b = new StringBuilder();
        b.append("?");
        for (Pair queryParam : queryParams) {
            if (!queryParam.getName().isEmpty()) {
                b.append(escapeString(queryParam.getName()));
                b.append("=");
                b.append(escapeString(queryParam.getValue()));
                b.append("&");
            }
        }

        return b.substring(0, b.length() - 1); // -1 to remove the last ampersand.
    }



    @Override
    public <T> RestHttpClientResponse<T> invokeGet(String path,
                                                   QueryParams queryParams,
                                                   HeaderParams headerParams,
                                                   TypeRef returnType
    ) throws ServiceException {
        return invokeApi(
                path, "GET",
                queryParams, headerParams,
                null, null,
                returnType
        );
    }

    @Override
    public <T> RestHttpClientResponse<T> invokeBody(String path, String method,
                                                    QueryParams queryParams,
                                                    HeaderParams headerParams,
                                                    Object body,
                                                    TypeRef returnType
    ) throws ServiceException {
        return invokeApi(
                path, method,
                queryParams, headerParams,
                body, null,
                returnType
        );
    }

    @Override
    public <T> RestHttpClientResponse<T> invokeForm(String path, String method,
                                                    QueryParams queryParams,
                                                    HeaderParams headerParams,
                                                    Map<String, Object> formParams,
                                                    TypeRef returnType
    ) throws ServiceException {
        return invokeApi(
                path, method,
                queryParams, headerParams,
                null, formParams,
                returnType
        );
    }

    /**
     * Invoke API by sending HTTP request with the given options.
     *
     * @param path The sub-path of the HTTP URL
     * @param method The request method, one of "GET", "POST", "PUT", and "DELETE"
     * @param queryParams The query parameters
     * @param body The request body object
     * @param headerParams The header parameters
     * @param formParams The form parameters
     * @param returnType The return type into which to deserialize the response
     */
    private <T> RestHttpClientResponse<T> invokeApi(String path, String method,
                                                   QueryParams queryParams, HeaderParams headerParams,
                                                   Object body, Map<String, Object> formParams,
                                                   TypeRef returnType
    ) throws ServiceException {

        Invocation.Builder invocationBuilder;
        Entity<?> formEntity = null;
        Entity<String> bodyEntity = null;


        //STEP 1/3: PREPARE DATA TO SEND
        try {
            if (body != null && formParams != null) {
                throw new IllegalArgumentException("Only formParams OR body can be used, not both at once!");
            }
            if (method.equals("GET") && (body != null || formParams != null)) {
                throw new IllegalArgumentException("Can't use GET with body nor form!");
            }
            if (body != null) {
                bodyEntity = serialize(body, CONTENT_TYPE);
            }

            Client client = getClient();
            WebTarget target = client.target(this.basePath).path(path);
            target = addQueryParams(queryParams, target);
            invocationBuilder = target.request().accept(ACCEPT);
            invocationBuilder = setHeaders(headerParams, invocationBuilder);

            if (CONTENT_TYPE.startsWith("multipart/form-data")) {
                MultiPart multipart = new MultiPart();
                for (Entry<String, Object> param : formParams.entrySet()) {
                    if (param.getValue() instanceof File) {
                        File file = (File) param.getValue();

                        FormDataMultiPart mp = new FormDataMultiPart();
                        mp.bodyPart(new FormDataBodyPart(param.getKey(), file.getName()));
                        multipart.bodyPart(mp, MediaType.MULTIPART_FORM_DATA_TYPE);

                        multipart.bodyPart(new FileDataBodyPart(param.getKey(), file, MediaType.APPLICATION_OCTET_STREAM_TYPE));
                    } else {
                        FormDataMultiPart mp = new FormDataMultiPart();
                        mp.bodyPart(new FormDataBodyPart(param.getKey(), parameterToString(param.getValue())));
                        multipart.bodyPart(mp, MediaType.MULTIPART_FORM_DATA_TYPE);
                    }
                }
                formEntity = Entity.entity(multipart, MediaType.MULTIPART_FORM_DATA_TYPE);
            } else if (CONTENT_TYPE.startsWith("application/x-www-form-urlencoded")) {
                Form form = new Form();
                for (Entry<String, Object> param : formParams.entrySet()) {
                    form.param(param.getKey(), parameterToString(param.getValue()));
                }
                formEntity = Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE);
            } else if (formParams != null) {
                throw new IllegalArgumentException("Form params can't be used for this content type!");
            }
        } catch (Exception e) {
            if (e instanceof ServiceException) {
                throw e;
            }
            throw new BadRequestServiceException(
                    new RestFaultInfo("BadRequest", Blame.CLIENT,
                            e.getMessage(), ""+ErrorCodes.Client.BAD_REQUEST.getCode(),
                            null,
                            Retry.no(), Retry.no(),
                            Response.Status.BAD_REQUEST.getStatusCode(),
                            Response.Status.BAD_REQUEST.getReasonPhrase()
                    ), e
            );
        }


        //STEP 2/3: SEND THE REQUEST
        Response response;
        switch (method) {
            case "GET":
                response = invocationBuilder.get();
                break;
            case "POST":
                if (formEntity != null) {
                    response = invocationBuilder.post(formEntity);
                } else if (body == null) {
                    response = invocationBuilder.post(null);
                } else {
                    response = invocationBuilder.post(bodyEntity);
                }
                break;
            case "PUT":
                if (formEntity != null) {
                    response = invocationBuilder.put(formEntity);
                } else if (body == null) {
                    response = invocationBuilder.put(null);
                } else {
                    response = invocationBuilder.put(bodyEntity);
                }
                break;
            case "DELETE":
                response = invocationBuilder.delete();
                break;
            default:
                String msg = "Unknown method type >>>" + method+"<<<!";
//                throw new WebApplicationException(msg, 500);
                throw new BadRequestServiceException(
                        new RestFaultInfo("BadRequest", Blame.CLIENT,
                                msg, null,
                                null,
                                Retry.no(), Retry.no(),
                                Response.Status.BAD_REQUEST.getStatusCode(),
                                Response.Status.BAD_REQUEST.getReasonPhrase()
                        )
                );
        }


        //STEP 3/3: READ/PARSE THE RESPONSE

        boolean envelope = false;

        if (response.getStatus() == Response.Status.NO_CONTENT.getStatusCode()) {
            //not sure who needs this...
            return new RestHttpClientResponse<>(response, Optional.<T>absent());
        } else if (response.getStatusInfo().getFamily().equals(Response.Status.Family.SUCCESSFUL)) {
            if (returnType == null)
                return new RestHttpClientResponse<>(response, Optional.<T>absent());
            else {
                T deserialized = deserializeResult(response, jsonEnvelopeResultAttributeName, returnType);
                return new RestHttpClientResponse<>(response, Optional.fromNullable(deserialized));
            }
        } else {
            TypeRef<RestFaultInfo> errorTypeRef = new TypeRef<RestFaultInfo>(){};
            RestFaultInfo faultInfo = deserializeResult(response, jsonEnvelopeErrorAttributeName, errorTypeRef);
            switch (faultInfo.getFaultCause()) {
                case "BadRequest":
                    throw new BadRequestServiceException(faultInfo);
                case "AccessDenied":
                    throw new AccessDeniedServiceException(faultInfo);
                case "InternalServerError":
                    throw new InternalServerErrorServiceException(faultInfo);
                case "ServiceTemporarilyUnavailable":
                    throw new ServiceTemporarilyUnavailableServiceException(faultInfo);
                case "BadResponse":
                    throw new BadResponseServiceException(faultInfo);
                default:
                    Blame.assertSize(4);
                    switch (faultInfo.getBlame()) {
                        case CLIENT:
                            throw new ClientServiceException(faultInfo);
                        case SERVER:
                            throw new ServerServiceException(faultInfo);
                        case NETWORK:
                            throw new NetworkServiceException(faultInfo);
                        case UNKNOWN:
                        default:
                            throw new ServiceException(faultInfo);
                    }
            }
        }
    }

    private Invocation.Builder setHeaders(@Nullable HeaderParams headerParams, @NotNull Invocation.Builder invocationBuilder) {
        if (headerParams != null) {
            for (Entry<String, String> entry : headerParams) {
                invocationBuilder = invocationBuilder.header(entry.getKey(), entry.getValue());
            }
        }
        if (defaultHeaders!=null) {
            for (Entry<String, String> defaultHeader : defaultHeaders) {
                if (headerParams==null || !headerParams.has(defaultHeader.getKey())) {
                    invocationBuilder = invocationBuilder.header(defaultHeader.getKey(), defaultHeader.getValue());
                }
            }
        }
        return invocationBuilder;
    }

    private WebTarget addQueryParams(QueryParams queryParams, WebTarget target) {
        if (queryParams != null) {
            for (Pair queryParam : queryParams) {
                target = target.queryParam(queryParam.getName(), queryParam.getValue());
            }
        }
        return target;
    }




    /**
     * Get an existing client or create a new client to handle HTTP request.
     */
    private Client getClient() {
        Client client = hostMap.get(basePath);
        if (client!=null) return client;

        final ClientConfig clientConfig = new ClientConfig();
        clientConfig.register(MultiPartFeature.class);
        if (debug) {
            clientConfig.register(LoggingFilter.class);
        }
        client = ClientBuilder.newClient(clientConfig);

        hostMap.putIfAbsent(basePath, client);
        return hostMap.get(basePath); //be sure to use the same in case of a race condition.
    }
}
