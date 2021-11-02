package by.epam.task3.entity;

import by.epam.task3.entity.enums.PreciousnessType;

import java.time.LocalDate;

public class SyntheticGem extends AbstractGem {
    private Producer producer = new Producer();
    private LocalDate dateOfProduction;

    public SyntheticGem() {
        super();
    }

    public SyntheticGem(String name, String gemId, PreciousnessType preciousnessType, VisualParameters parameters, double value, Producer producer, LocalDate dateOfProduction) {
        super(name, gemId, preciousnessType, parameters, value);
        this.producer = producer;
        this.dateOfProduction = dateOfProduction;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    public LocalDate getDateOfProduction() {
        return dateOfProduction;
    }

    public void setDateOfProduction(LocalDate dateOfProduction) {
        this.dateOfProduction = dateOfProduction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SyntheticGem that = (SyntheticGem) o;
        return producer != null ? producer.equals(that.producer) : that.producer == null &&
                dateOfProduction.equals(that.dateOfProduction);
    }

    @Override
    public int hashCode() {
        int result = 11;
        result += 37 * result + producer.hashCode();
        result += 37 * result + dateOfProduction.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("\nSyntheticGem{");
        sb.append("producer=").append(producer);
        sb.append(", dateOfProduction=").append(dateOfProduction);
        sb.append('}');
        return sb.toString();
    }
}
