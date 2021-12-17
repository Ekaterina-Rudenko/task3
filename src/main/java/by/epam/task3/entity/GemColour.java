package by.epam.task3.entity;

public enum GemColour {
    WHITE,
    GREEN,
    RED,
    BLUE,
    YELLOW,
    BLACK,
    PINK,
    PURPLE,
    COLOURLESS;

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
