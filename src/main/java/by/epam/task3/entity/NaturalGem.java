package by.epam.task3.entity;

import by.epam.task3.entity.enums.Country;
import by.epam.task3.entity.enums.PreciousnessType;

import java.time.LocalDate;

public class NaturalGem extends AbstractGem {
    private Country originCountry;
    private LocalDate extractionDate;

    public NaturalGem() {
        super();
    }

    public NaturalGem(String name, String gemId, PreciousnessType preciousnessType, VisualParameters parameters, double value, Country originCountry, LocalDate extractionDate) {
        super(name, gemId, preciousnessType, parameters, value);
        this.originCountry = originCountry;
        this.extractionDate = extractionDate;
    }

    public Country getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(Country originCountry) {
        this.originCountry = originCountry;
    }

    public LocalDate getExtractionDate() {
        return extractionDate;
    }

    public void setExtractionDate(LocalDate extractionDate) {
        this.extractionDate = extractionDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        NaturalGem that = (NaturalGem) o;
        return (originCountry != null ? originCountry.equals(that.originCountry) : that.originCountry == null) &&
                extractionDate.equals(that.extractionDate);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result += 37 * result + originCountry.hashCode();
        result += 37 * result + extractionDate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("\nNaturalGem{");
        sb.append(" name=").append(this.getName());
        sb.append(", id=").append(this.getGemId());
        sb.append(", preciousness=").append(this.getPreciousness());
        sb.append(", ").append(this.getParameters());
        sb.append(" gemValue=").append(this.getValue());
        sb.append(", originCountry=").append(originCountry);
        sb.append(" extractionDate=").append(extractionDate);
        sb.append('}');
        return sb.toString();
    }
}
