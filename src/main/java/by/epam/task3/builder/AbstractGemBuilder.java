package by.epam.task3.builder;

import by.epam.task3.entity.AbstractGem;
import by.epam.task3.exception.GemException;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractGemBuilder {
    protected Set<AbstractGem> gems;

    public AbstractGemBuilder() {
        this.gems = new HashSet<AbstractGem>();
    }

    public AbstractGemBuilder(Set <AbstractGem> gems) {
        this.gems = gems;
    }

    public Set<AbstractGem> getGems() {
        return gems;
    }

    public abstract void buildGems(String filename) throws GemException;
}
