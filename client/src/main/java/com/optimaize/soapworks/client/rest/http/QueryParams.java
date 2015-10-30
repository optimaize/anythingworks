package com.optimaize.soapworks.client.rest.http;

import java.util.*;

/**
 * Collector for params to append to the URL.
 *
 * In contrast to HeaderParams each name can exist more than once.
 * Does not contain null or empty names nor values. Trims names and values.
 */
public class QueryParams implements Iterable<Pair> {


    public static final QueryParams NONE = new QueryParams(Collections.<Pair>emptyList());

    /**
     * Returns an impl that is empty and does not accept any params.
     * Use this when you don't need any, it avoid object creation and garbage collection.
     */
    public static QueryParams none() {
        return NONE;
    }

    public static QueryParams create() {
        return new QueryParams();
    }

    public static QueryParams create(QueryParams base) {
        return new QueryParams(new ArrayList<>(base.values));
    }



    private final List<Pair> values;

    private QueryParams() {
        values = new ArrayList<>();
    }
    private QueryParams(List<Pair> values) {
        this.values = values;
    }


    /**
     * @return the same instance
     */
    public QueryParams add(String name, String value) {
        values.add(new Pair(name, value));
        return this;
    }


    @Override
    public Iterator<Pair> iterator() {
        return values.iterator();
    }
}
