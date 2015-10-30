package com.optimaize.soapworks.common.rest;

/**
 *
 */
public interface JsonMarshaller {

    /**
     * Converts an object to JSON.
     * @param obj Any Java object that is marshallable to Json, either because it is a POJO or has annotations
     *            or there is a custom mapper registered.
     * @return The object as string, or null if it is null.
     * @throws JsonMarshallingException
     */
    String serialize(Object obj) throws JsonMarshallingException;

    /**
     * Converts JSON to an object of the specified type.
     * @throws JsonMarshallingException
     */
    <T> T deserialize(String body, TypeRef returnType) throws JsonMarshallingException;

    /**
     * Converts JSON to an object of the specified type by taking the field of the envelope.
     *
     * Example: the JSON looks like {success: true, result: {...}}
     * Then a call like deserializeEnveloped(json, "result", myType) is appropriate.
     *
     * @throws JsonMarshallingException
     */
    <T> T deserializeEnveloped(String body, String envelopeAttributeName, TypeRef returnType) throws JsonMarshallingException;

}
