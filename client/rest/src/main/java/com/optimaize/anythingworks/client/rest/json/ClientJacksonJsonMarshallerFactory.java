package com.optimaize.anythingworks.client.rest.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.optimaize.anythingworks.common.rest.JacksonJsonMarshaller;
import org.jetbrains.annotations.NotNull;

/**
 * Factory for creating {@link JacksonJsonMarshaller} instances to be used by the client.
 */
public class ClientJacksonJsonMarshallerFactory {

    public static JacksonJsonMarshaller create(ObjectMapper jackson) {
        return new JacksonJsonMarshaller(jackson);
    }

    public static JacksonJsonMarshaller create() {
        return new JacksonJsonMarshaller(makeDefaultMapper());
    }

    @NotNull
    private static ObjectMapper makeDefaultMapper() {
        ObjectMapper mapper = new ObjectMapper();

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);

        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        mapper.enable(SerializationFeature.INDENT_OUTPUT); //disputable, very disputable for sending to the server.
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL); //exclude null

        //for Guava Optional:
        GuavaModule module = new GuavaModule();
        mapper.registerModule(module);

        return mapper;
    }

}
