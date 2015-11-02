package com.optimaize.anythingworks.client.rest.http;

import java.util.*;

/**
 * Collector for params to put into the http header.
 *
 * In contrast to QueryParams each name can only exist once here.
 * Does not contain null or empty names nor values. Trims names and values.
 */
public class HeaderParams implements Iterable<Map.Entry<String,String>> {

    public static final HeaderParams NONE = new HeaderParams(Collections.<String, String>emptyMap());

    /**
     * Returns an impl that is empty and does not accept any params.
     * Use this when you don't need any, it avoid object creation and garbage collection.
     */
    public static HeaderParams none() {
        return NONE;
    }

    public static HeaderParams create() {
        return new HeaderParams();
    }

    public static HeaderParams create(HeaderParams base) {
        return new HeaderParams(new LinkedHashMap<>(base.values));
    }



    private final Map<String,String> values;

    private HeaderParams() {
        values = new LinkedHashMap<>();
    }
    private HeaderParams(Map<String,String> values) {
        this.values = values;
    }


    /**
     * Overwrites in case there is a value already associated with that name.
     * @return the same instance
     */
    public HeaderParams put(String name, String value) {
        values.put(cleanAndValidate(name), cleanAndValidate(value));
        return this;
    }

    public HeaderParams userAgent(String value) {
        return put("User-Agent", value);
    }

    public boolean has(String name) {
        return values.containsKey(name);
    }


    private static String cleanAndValidate(String arg) {
        if (arg == null) {
            throw new IllegalArgumentException("Null value provided!");
        }
        arg = arg.trim();
        if (arg.isEmpty()) {
            throw new IllegalArgumentException("Empty value provided!");
        }
        return arg;
    }

    @Override
    public Iterator<Map.Entry<String,String>> iterator() {
        return values.entrySet().iterator();
    }
}
