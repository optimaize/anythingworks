package com.optimaize.soapworks.exampleproject.ontology.rest.development.post;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

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

    private String string;
    private int number;
    private boolean yesOrNo;
    private Color color;
    private GeometricalFigure geometricalFigure;

    @JsonCreator
    public ComplexObject(
            @JsonProperty("string") String string,
            @JsonProperty("number") int number,
            @JsonProperty("yesOrNo") boolean yesOrNo,
            @JsonProperty("color") Color color,
            @JsonProperty("geometricalFigure") GeometricalFigure geometricalFigure
    ) {
        this.string = string;
        this.number = number;
        this.yesOrNo = yesOrNo;
        this.color = color;
        this.geometricalFigure = geometricalFigure;
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

}
