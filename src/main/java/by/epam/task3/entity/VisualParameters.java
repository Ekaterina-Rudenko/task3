package by.epam.task3.entity;

import by.epam.task3.entity.enums.GemColour;

public class VisualParameters {
    public static final int DEFAULT_TRANSPARENCY = 0;
    public static final int DEFAULT_CUT = 4;

    private GemColour colour;
    private int transparency;
    private int cut;

    public VisualParameters() {
        this.colour = GemColour.COLOURLESS;
        this.transparency = DEFAULT_TRANSPARENCY;
        this.cut = DEFAULT_CUT;
    }

    public VisualParameters(GemColour colour, int transparency, int cut) {
        this.colour = colour;
        this.transparency = transparency;
        this.cut = cut;
    }

    public GemColour getColour() {
        return colour;
    }

    public void setColour(GemColour colour) {
        this.colour = colour;
    }

    public double getTransparency() {
        return transparency;
    }

    public void setTransparency(int transparency) {
        this.transparency = transparency;
    }

    public int getCut() {
        return cut;
    }

    public void setCut(int cut) {
        this.cut = cut;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VisualParameters that = (VisualParameters) o;
        return colour != null ? colour.equals(that.colour) : that.colour == null
                && transparency == that.transparency
                && cut == that.cut;
    }

    @Override
    public int hashCode() {
        int result = 11;
        result += 37 * result + colour.hashCode();
        result += 37 * result + transparency;
        result += 37 * result + cut;
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("VisualParameters{");
        sb.append("colour=").append(colour);
        sb.append(", transparency=").append(transparency);
        sb.append(", cut=").append(cut);
        sb.append('}');
        return sb.toString();
    }
}
