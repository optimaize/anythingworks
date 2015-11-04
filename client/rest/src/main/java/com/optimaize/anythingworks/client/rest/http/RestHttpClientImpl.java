package com.optimaize.anythingworks.client.rest.http;

import com.google.common.base.Optional;
import com.optimaize.anythingworks.client.rest.json.ClientJacksonJsonMarshallerFactory;
import com.optimaize.anythingworks.common.rest.JsonError;
import com.optimaize.anythingworks.common.rest.JsonMarshaller;
import com.optimaize.anythingworks.common.rest.JsonMarshallingException;
import com.optimaize.anythingworks.common.rest.TypeRef;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.ws.rs.*;
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
    private static final String CONTENT_TYPE = "application/json";


    /**
     * Creation of these Client instances is expensive, and according to its Javadoc, they
     * should be shared.
     */
    private static ConcurrentMap<String, Client> hostMap = new ConcurrentHashMap<>();



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

    /*
      Format to {@code Pair} objects.
    */
    private List<Pair> parameterToPairs(String collectionFormat, String name, Object value) {
        List<Pair> params = new ArrayList<>();

        // preconditions
        if (name == null || name.isEmpty() || value == null) return params;

        Collection valueCollection;
        if (value instanceof Collection) {
            valueCollection = (Collection) value;
        } else {
            params.add(new Pair(name, parameterToString(value)));
            return params;
        }

        if (valueCollection.isEmpty()) {
            return params;
        }

        // get the collection format
        collectionFormat = (collectionFormat == null || collectionFormat.isEmpty() ? "csv" : collectionFormat); // default: csv

        // create the params based on the collection format
        if (collectionFormat.equals("multi")) {
            for (Object item : valueCollection) {
                params.add(new Pair(name, parameterToString(item)));
            }

            return params;
        }

        String delimiter = ",";

        if (collectionFormat.equals("csv")) {
            delimiter = ",";
        } else if (collectionFormat.equals("ssv")) {
            delimiter = " ";
        } else if (collectionFormat.equals("tsv")) {
            delimiter = "\t";
        } else if (collectionFormat.equals("pipes")) {
            delimiter = "|";
        }

        StringBuilder sb = new StringBuilder();
        for (Object item : valueCollection) {
            sb.append(delimiter);
            sb.append(parameterToString(item));
        }

        params.add(new Pair(name, sb.substring(1)));

        return params;
    }

    /**
     * Select the Accept header's value from the given accepts array:
     * if JSON exists in the given array, use it;
     * otherwise use all of them (joining into a string)
     *
     * @param accepts The accepts array to select from
     * @return The Accept header to use. If the given array is empty,
     * null will be returned (not to set the Accept header explicitly).
     */
    private String selectHeaderAccept(String[] accepts) {
        if (accepts.length == 0) return null;
        if (StringUtil.containsIgnoreCase(accepts, "application/json")) return "application/json";
        return StringUtil.join(accepts, ",");
    }

    /**
     * Select the Content-Type header's value from the given array:
     * if JSON exists in the given array, use it;
     * otherwise use the first one of the array.
     *
     * @param contentTypes The Content-Type array to select from
     * @return The Content-Type header to use. If the given array is empty,
     * JSON will be used.
     */
    private String selectHeaderContentType(String[] contentTypes) {
        if (contentTypes.length == 0) return "application/json";
        if (StringUtil.containsIgnoreCase(contentTypes, "application/json")) return "application/json";
        return contentTypes[0];
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
     */
    private Entity<String> serialize(Object obj, String contentType) throws WebApplicationException {
        if (contentType.startsWith("application/json")) {
            try {
                return Entity.json(jsonMarshaller.serialize(obj));
            } catch (JsonMarshallingException e) {
                throw new ClientErrorException(Response.Status.BAD_REQUEST, e);
            }
        } else {
            throw new NotSupportedException(
                    "Content type \"" + contentType + "\" is not supported."
            );
        }
    }

    /**
     * Deserialize response body to Java object according to the Content-Type.
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
                throw new ClientErrorException(Response.Status.BAD_REQUEST, e);
            }
        } else if (returnType.getType().equals(String.class)) {
            // Expecting string, return the raw response body.
            return (T) body;
        } else {
            throw new NotSupportedException(
                    "Content type \"" + contentType + "\" is not supported for type: "
                            + returnType.getType()
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
        if (contentTypes != null && !contentTypes.isEmpty())
            contentType = String.valueOf(contentTypes.get(0));
        if (contentType == null)
            throw new WebApplicationException("Missing Content-Type in response", 500);
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
    ) throws WebApplicationException {
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
    ) throws WebApplicationException {
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
    ) throws WebApplicationException {
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
    ) throws WebApplicationException {
        if (body!=null && formParams!=null) {
            throw new IllegalArgumentException("Only formParams OR body can be used, not both at once!");
        }
        if (method.equals("GET") && (body!=null || formParams!=null)) {
            throw new IllegalArgumentException("Can't use GET with body nor form!");
        }

        Client client = getClient();
        WebTarget target = client.target(this.basePath).path(path);
        target = addQueryParams(queryParams, target);
        Invocation.Builder invocationBuilder = target.request().accept(ACCEPT);
        invocationBuilder = setHeaders(headerParams, invocationBuilder);

        Entity<?> formEntity = null;

        if (CONTENT_TYPE.startsWith("multipart/form-data")) {
            MultiPart multipart = new MultiPart();
            for (Entry<String, Object> param: formParams.entrySet()) {
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
            for (Entry<String, Object> param: formParams.entrySet()) {
                form.param(param.getKey(), parameterToString(param.getValue()));
            }
            formEntity = Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED_TYPE);
        } else if (formParams!=null) {
            throw new IllegalArgumentException("Form params can't be used for this content type!");
        }

        Response response;

        if ("GET".equals(method)) {
            response = invocationBuilder.get();
        } else if ("POST".equals(method)) {
            if (formEntity != null) {
                response = invocationBuilder.post(formEntity);
            } else if (body == null) {
                response = invocationBuilder.post(null);
            } else {
                response = invocationBuilder.post(serialize(body, CONTENT_TYPE));
            }
        } else if ("PUT".equals(method)) {
            if (formEntity != null) {
                response = invocationBuilder.put(formEntity);
            } else if (body == null) {
                response = invocationBuilder.put(null);
            } else {
                response = invocationBuilder.put(serialize(body, CONTENT_TYPE));
            }
        } else if ("DELETE".equals(method)) {
            response = invocationBuilder.delete();
        } else {
            throw new WebApplicationException("Unknown method type " + method, 500);
        }

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

            String message = null;
            try {
                TypeRef<JsonError> errorTypeRef = new TypeRef<JsonError>(){};
                JsonError restError = deserializeResult(response, jsonEnvelopeErrorAttributeName, errorTypeRef);
                if (restError!=null) {
                    message = restError.getErrorText();
                }
            } catch (WebApplicationException e) {
                //never mind
            }

            //source, but modified: http://stackoverflow.com/questions/22561527/handling-custom-error-response-in-jax-rs-2-0-client-library
            Response.Status status = Response.Status.fromStatusCode(response.getStatus());
            if (message==null) {
                message = status.getReasonPhrase();
            }
            WebApplicationException webAppException;
            switch (status) {

                case BAD_REQUEST:
                    webAppException = new BadRequestException(message);
                    break;
                case UNAUTHORIZED:
                    webAppException = new NotAuthorizedException(message);
                    break;
                case FORBIDDEN:
                    webAppException = new ForbiddenException(message);
                    break;
                case NOT_FOUND:
                    webAppException = new NotFoundException(message);
                    break;
                case METHOD_NOT_ALLOWED:
                    webAppException = new NotAllowedException(message);
                    break;
                case NOT_ACCEPTABLE:
                    webAppException = new NotAcceptableException(message);
                    break;
                case UNSUPPORTED_MEDIA_TYPE:
                    webAppException = new NotSupportedException(message);
                    break;

                case INTERNAL_SERVER_ERROR:
                    webAppException = new InternalServerErrorException(message);
                    break;
                case SERVICE_UNAVAILABLE:
                    webAppException = new ServiceUnavailableException(message);
                    break;

                default:
                    if (status.getFamily() == Response.Status.Family.CLIENT_ERROR) {
                        webAppException = new ClientErrorException(message, status.getStatusCode());
                    } else if (status.getFamily() == Response.Status.Family.SERVER_ERROR) {
                        webAppException = new ServerErrorException(message, status.getStatusCode());
                    } else {
                        //3xx codes (dunno what to throw)
                        webAppException = new WebApplicationException(message);
                    }
            }

            throw webAppException;
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
