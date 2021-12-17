package by.epam.task3.entity;

public enum Country {
    USA("USA"),
    RUSSIA("Russia"),
    SOUTH_AFRICA("South_Africa"),
    ANGOLA("Angola"),
    ALGERIA("Algeria"),
    CANADA("Canada"),
    BRAZIL("Brazil"),
    VENEZUELA("Venezuela"),
    AUSTRALIA("Australia"),
    SWITZERLAND("Switzerland"),
    INDIA("India"),
    MADAGASCAR("Madagascar"),
    COLUMBIA("Columbia"),
    SWEDEN("Sweden"),
    CHINA("China"),
    BELGIUM("Belgium"),
    SOUTH_KOREA("South_Korea"),
    JAPAN("Japan");

    private String value;

    Country(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Country{");
        sb.append("value='").append(value).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
