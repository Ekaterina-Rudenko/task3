package by.epam.task3.handler;

public enum GemXMLTag {
/*    GEMS("gems"),
    NATURAL("natural"),
    SYNTHETIC("synthetic"),
    NAME("name"),
    ID("id"),
    PRECIOUSNESS("preciousness"),
    VISUAL_PARAMETERS("visual-parameters"),
    COLOUR("colour"),
    TRANSPARENCY("transparency"),
    CUT("cut"),
    VALUE("value"),
    ORIGIN_COUNTRY("origin-country"),
    EXTRACTION_DATE("extraction-date"),
    COMPANY("company"),
    HEADQUARTER("headquarter"),
    PRODUCTION_DATE("production-date");*/

/*
    private String value;

    GemXMLTag(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
*/

    GEMS,
    NATURAL,
    SYNTHETIC,
    NAME,
    PRECIOUSNESS,
    ID,
    VISUAL_PARAMETERS,
    COLOUR,
    TRANSPARENCY,
    CUT,
    VALUE,
    ORIGIN_COUNTRY,
    EXTRACTION_DATE,
    COMPANY,
    HEADQUARTER,
    PRODUCTION_DATE,
    PRODUCER;

    public String toString() {
        String stringTag = name().replace("_", "-").toLowerCase();
        return stringTag;
    }
}
