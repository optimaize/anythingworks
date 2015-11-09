package com.optimaize.anythingworks.exampleproject.ontology.rest.development.post;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;

/**
 * A transfer object that contains different data types, and a nested polymorphic object.
 *
 * <pre>
 * - string
 * - integer
 * - boolean
 * - enum
 * - polymorphic object GeometricalFigure with subtypes Circle and Rectangle
 * </pre>
 *
 */
public class ComplexObject {

    public enum Color {
        RED,
        GREEN,
        BLUE
    }

    private final String string;
    private final int number;
    private final boolean yesOrNo;
    private final Color color;
    private final GeometricalFigure geometricalFigure;
    private final Optional<String> optional1;
    private final Optional<String> optional2;

    @JsonCreator
    public ComplexObject(
            @JsonProperty("string") String string,
            @JsonProperty("number") int number,
            @JsonProperty("yesOrNo") boolean yesOrNo,
            @JsonProperty("color") Color color,
            @JsonProperty("geometricalFigure") GeometricalFigure geometricalFigure,
            @JsonProperty("optional1") Optional<String> optional1,
            @JsonProperty("optional2") Optional<String> optional2
    ) {
        this.string = string;
        this.number = number;
        this.yesOrNo = yesOrNo;
        this.color = color;
        this.geometricalFigure = geometricalFigure;
        this.optional1 = optional1;
        this.optional2 = optional2;
    }


    public String getString() {
        return string;
    }

    public int getNumber() {
        return number;
    }

    public boolean isYesOrNo() {
        return yesOrNo;
    }

    public Color getColor() {
        return color;
    }

    public GeometricalFigure getGeometricalFigure() {
        return geometricalFigure;
    }

    public Optional<String> getOptional1() {
        return optional1;
    }

    public Optional<String> getOptional2() {
        return optional2;
    }
}
