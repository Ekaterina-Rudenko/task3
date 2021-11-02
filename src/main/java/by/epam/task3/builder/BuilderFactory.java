package by.epam.task3.builder;

import by.epam.task3.exception.GemException;

public class BuilderFactory {
    private enum ParserType {
        DOM,
        SAX,
        STAX
    }

    private BuilderFactory() {
    }

    public static AbstractGemBuilder createGemsBuilder(String parserType) throws GemException {
        ParserType type = ParserType.valueOf(parserType.toUpperCase());
        switch (type) {
            case DOM:
                return new DomGemBuilder();
            case SAX:
                return new SaxGemBuilder();
            case STAX:
                return new StaxGemBuilder();
            default:
                throw new EnumConstantNotPresentException(
                        type.getDeclaringClass(), type.name()
                );
        }
    }
}
