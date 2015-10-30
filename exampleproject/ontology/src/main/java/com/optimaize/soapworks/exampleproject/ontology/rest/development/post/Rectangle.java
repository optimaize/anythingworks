package com.optimaize.soapworks.exampleproject.ontology.rest.development.post;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 */
public class Rectangle extends GeometricalFigure {

    private double length;
    private double width;

    @JsonCreator
    public Rectangle(
            @JsonProperty("color") String color,
            @JsonProperty("length") double length,
            @JsonProperty("width") double width) {
        super(color);
        this.length = length;
        this.width = width;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

}
