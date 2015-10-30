package com.optimaize.soapworks.common.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.optimaize.soapworks.common.rest.JsonMarshaller;
import com.optimaize.soapworks.common.rest.JsonMarshallingException;
import com.optimaize.soapworks.common.rest.TypeRef;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class JacksonJsonMarshaller implements JsonMarshaller {

    @NotNull
    private final ObjectMapper jackson;

    public JacksonJsonMarshaller(@NotNull ObjectMapper jackson) {
        this.jackson = jackson;
    }


    @NotNull
    public ObjectMapper getJackson() {
        return jackson;
    }


    /**
     * Serialize the given Java object into JSON string.
     */
    @Override
    public String serialize(Object obj) throws JsonMarshallingException {
        try {
            return serializeChecked(obj);
        } catch (Exception e) {
            throw new JsonMarshallingException(e);
        }
    }

    public String serializeChecked(Object obj) throws JsonProcessingException {
        if (obj == null) {
            return null;
        }
        return jackson.writeValueAsString(obj);
    }


    /**
     * Deserialize the given JSON string to Java object.
     *
     * @param body       The JSON string
     * @param returnType The type to deserialize inot
     * @return The deserialized Java object
     */
    @Override
    public <T> T deserialize(String body, TypeRef returnType) throws JsonMarshallingException {
        try {
            return deserializeChecked(body, returnType);
        } catch (Exception e) {
            throw new JsonMarshallingException(e);
        }
    }
    public <T> T deserializeChecked(String body, TypeRef returnType) throws IOException {
        if (returnType.getType() == String.class) {
            //noinspection unchecked
            return (T) body;
        }
        JavaType javaType = jackson.constructType(returnType.getType());
        return jackson.readValue(body, javaType);
    }


    @Override
    public <T> T deserializeEnveloped(String body, String envelopeAttributeName, TypeRef returnType) throws JsonMarshallingException {
        try {
            return deserializeEnvelopedChecked(body, envelopeAttributeName, returnType);
        } catch (Exception e) {
            throw new JsonMarshallingException(e);
        }
    }
    public <T> T deserializeEnvelopedChecked(String body, String envelopeAttributeName, TypeRef returnType) throws IOException {
        body = jackson.readTree(body).get(envelopeAttributeName).toString();
        return deserializeChecked(body, returnType);
    }

}
