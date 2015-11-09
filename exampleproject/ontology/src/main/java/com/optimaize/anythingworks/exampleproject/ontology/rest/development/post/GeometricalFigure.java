package com.optimaize.anythingworks.exampleproject.ontology.rest.development.post;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 *
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value=Circle.class, name = "circle"),
        @JsonSubTypes.Type(value=Rectangle.class, name="rectangle")
})
public class GeometricalFigure {

    private final String color;

    @JsonCreator
    public GeometricalFigure(
            @JsonProperty("color") String color
    ) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
