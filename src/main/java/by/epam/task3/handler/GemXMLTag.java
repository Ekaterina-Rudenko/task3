package by.epam.task3.handler;

public enum GemXMLTag {

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
