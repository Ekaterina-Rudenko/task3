package by.epam.task3.entity;

public class Producer {
    private String company;
    private Country country;

    public Producer() {
    }

    public Producer(String company, Country country) {
        this.company = company;
        this.country = country;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producer producer = (Producer) o;
        return company != null ? company.equals(producer.company) : producer.company == null &&
                country != null ? country.equals(producer.country) : producer.country == null;
    }

    @Override
    public int hashCode() {
        int result = 11;
        result += 37 * result + ((company==null)? company.hashCode() : 0);
        result += 37 * result + ((country == null) ? country.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Producer{");
        sb.append("company='").append(company).append('\'');
        sb.append(", country=").append(country);
        sb.append('}');
        return sb.toString();
    }
}
