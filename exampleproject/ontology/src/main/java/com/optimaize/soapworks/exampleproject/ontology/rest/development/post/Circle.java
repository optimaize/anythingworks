package com.optimaize.soapworks.exampleproject.ontology.rest.development.post;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 */
public class Circle extends GeometricalFigure {

    private final double radius;

    @JsonCreator
    public Circle(
            @JsonProperty("color") String color,
            @JsonProperty("radius") double radius) {
        super(color);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }
}
