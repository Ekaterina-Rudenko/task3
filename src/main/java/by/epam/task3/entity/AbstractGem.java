package by.epam.task3.entity;

public abstract class AbstractGem {

    private String name;
    private String gemId;
    private PreciousnessType preciousnessType;
    private VisualParameters parameters;
    private double value;

    public AbstractGem() {
        parameters = new VisualParameters();
        preciousnessType = PreciousnessType.PRECIOUS;
    }

    public AbstractGem(String name, String gemId, PreciousnessType preciousnessType, VisualParameters parameters, double value) {
        this.name = name;
        this.gemId = gemId;
        this.preciousnessType = preciousnessType;
        this.parameters = parameters;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGemId() {
        return gemId;
    }

    public void setGemId(String gemId) {
        this.gemId = gemId;
    }

    public PreciousnessType getPreciousness() {
        return preciousnessType;
    }

    public void setPreciousness(PreciousnessType preciousnessType) {
        this.preciousnessType = preciousnessType;
    }

    public VisualParameters getParameters() {
        return parameters;
    }

    public void setParameters(VisualParameters parameters) {
        this.parameters = parameters;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractGem gem = (AbstractGem) o;
        return Double.compare(gem.value, value) == 0
                && name != null ? name.equals(gem.name) : gem.name == null
                && gemId != null ? gemId.equals(gem.gemId) : gem.gemId == null
                && preciousnessType == gem.preciousnessType
                && (parameters != null ? parameters.equals(gem.parameters) : gem.parameters == null);
    }

    @Override
    public int hashCode() {
        int result = 11;
        result += 31 * result + name.hashCode();
        result += 31 * result + gemId.hashCode();
        result += 31 * result + preciousnessType.hashCode();
        result += 31 * result + parameters.hashCode();
        result += 31 * result + value;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AbstractGem{");
        sb.append("name='").append(name).append('\'');
        sb.append(", gemId='").append(gemId).append('\'');
        sb.append(", preciousness=").append(preciousnessType);
        sb.append(", parameters=").append(parameters);
        sb.append(", value=").append(value);
        sb.append('}');
        return sb.toString();
    }
}
