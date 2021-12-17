package by.epam.task3.entity;

public enum PreciousnessType {
    PRECIOUS,
    SEMIPRECIOUS;

    @Override
    public String toString(){
        return this.name().toLowerCase();
    }
}
