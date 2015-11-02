package com.optimaize.anythingworks.client.rest.http;


/**
 * Bundles a string name with a string value.
 *
 * Does not allow null nor empty names or values.
 * Trims names and values.
 */
public class Pair {

    private final String name;
    private final String value;

    public Pair(String name, String value) {
        this.name = cleanAndValidate(name);
        this.value = cleanAndValidate(value);
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


    public String getName() {
        return this.name;
    }


    public String getValue() {
        return this.value;
    }


    @Override
    public String toString() {
        return "Pair{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pair pair = (Pair) o;

        if (name != null ? !name.equals(pair.name) : pair.name != null) return false;
        return !(value != null ? !value.equals(pair.value) : pair.value != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
