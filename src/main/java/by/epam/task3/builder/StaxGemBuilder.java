package by.epam.task3.builder;

import by.epam.task3.entity.*;
import by.epam.task3.entity.enums.Country;
import by.epam.task3.entity.enums.GemColour;
import by.epam.task3.entity.enums.PreciousnessType;
import by.epam.task3.exception.GemException;
import by.epam.task3.handler.GemXMLTag;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Set;

public class StaxGemBuilder extends AbstractGemBuilder {
    static Logger logger = LogManager.getLogger();
    private XMLInputFactory inputFactory;
    static final String UNDERSCORE = "_";
    static final String HYPHEN = "-";

    public StaxGemBuilder() {
        super();
        inputFactory = XMLInputFactory.newInstance();
    }

    @Override
    public Set<AbstractGem> getGems() {
        return super.getGems();
    }

    @Override
    public void buildGems(String filePath) throws GemException {
        XMLStreamReader reader;
        String name;
        try (FileInputStream inputStream = new FileInputStream(filePath)) {
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (name.equals(GemXMLTag.NATURAL.toString())) {
                        NaturalGem naturalGem = buildNatural(reader);
                        gems.add(naturalGem);
                    } else if (name.equals(GemXMLTag.SYNTHETIC.toString())) {
                        SyntheticGem syntheticGem = buildSynthetic(reader);
                        gems.add(syntheticGem);
                    }
                }
            }
        } catch (XMLStreamException | FileNotFoundException e) {
            logger.log(Level.ERROR, "Error in Stax: " + e.getMessage());
            throw new GemException("Error in Stax: " + e.getMessage());
        } catch (IOException e) {
            logger.log(Level.ERROR, "Error in Stax, check your filename: " + filePath, e);
            throw new GemException("Error in Stax, check your filename: " + filePath, e);
        }
        logger.log(Level.INFO, "Gems from StAX builder are:" );
    }

    private NaturalGem buildNatural(XMLStreamReader reader) throws XMLStreamException {
        NaturalGem naturalGem = new NaturalGem();
        fillAttribute(naturalGem, reader);
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (GemXMLTag.valueOf(name.toUpperCase().replace(HYPHEN, UNDERSCORE))) {
                        case ID:
                            naturalGem.setGemId(getXMLText(reader));
                            break;
                        case VISUAL_PARAMETERS:
                            naturalGem.setParameters(getXMLVisualParameters(reader));
                            break;
                        case VALUE:
                            naturalGem.setValue(Double.parseDouble(getXMLText(reader)));
                            break;
                        case ORIGIN_COUNTRY:
                            Country country = Country.valueOf(getXMLText(reader).toUpperCase());
                            naturalGem.setOriginCountry(country);
                            break;
                        case EXTRACTION_DATE:
                            naturalGem.setExtractionDate(LocalDate.parse(getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (GemXMLTag.valueOf(name.toUpperCase().replace(HYPHEN, UNDERSCORE)).equals(GemXMLTag.NATURAL)) {
                        return naturalGem;
                    }
            }
        }
        throw new XMLStreamException("Unknown element in tag <natural>");
    }

    private SyntheticGem buildSynthetic(XMLStreamReader reader) throws XMLStreamException {
        SyntheticGem syntheticGem = new SyntheticGem();
        fillAttribute(syntheticGem, reader);
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (GemXMLTag.valueOf(name.toUpperCase().replace(HYPHEN, UNDERSCORE))) {
                        case ID:
                            syntheticGem.setGemId(getXMLText(reader));
                            break;
                        case VISUAL_PARAMETERS:
                            syntheticGem.setParameters(getXMLVisualParameters(reader));
                            break;
                        case VALUE:
                            syntheticGem.setValue(Double.parseDouble(getXMLText(reader)));
                            break;
                        case PRODUCER:
                            syntheticGem.setProducer(getXMLProducer(reader));
                            break;
                        case PRODUCTION_DATE:
                            syntheticGem.setDateOfProduction(LocalDate.parse(getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (GemXMLTag.valueOf(name.toUpperCase().replace(HYPHEN, UNDERSCORE)).equals(GemXMLTag.SYNTHETIC)) {
                        return syntheticGem;
                    }
            }
        }
        throw new XMLStreamException("Unknown element in tag <synthetic>");
    }

    private Producer getXMLProducer(XMLStreamReader reader) throws XMLStreamException {
        Producer producer = new Producer();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (GemXMLTag.valueOf(name.toUpperCase().replace(HYPHEN, UNDERSCORE))) {
                        case COMPANY:
                            producer.setCompany(getXMLText(reader));
                            break;
                        case HEADQUARTER:
                            producer.setCountry(Country.valueOf(getXMLText(reader).toUpperCase()));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (GemXMLTag.valueOf(name.toUpperCase().replace(HYPHEN, UNDERSCORE)).equals(GemXMLTag.PRODUCER)) {
                        return producer;
                    }
            }
        }
        throw new XMLStreamException("Unknown element in tag <producer>");
    }

    private VisualParameters getXMLVisualParameters(XMLStreamReader reader) throws XMLStreamException {
        VisualParameters visualParameters = new VisualParameters();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (GemXMLTag.valueOf(name.toUpperCase().replace(HYPHEN, UNDERSCORE))) {
                        case COLOUR:
                            visualParameters.setColour(GemColour.valueOf(getXMLText(reader).toUpperCase()));
                            break;
                        case TRANSPARENCY:
                            visualParameters.setTransparency(Integer.parseInt(getXMLText((reader))));
                            break;
                        case CUT:
                            visualParameters.setCut(Integer.parseInt(getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (GemXMLTag.valueOf(name.toUpperCase().replace(HYPHEN, UNDERSCORE)).equals(GemXMLTag.VISUAL_PARAMETERS)) {
                        return visualParameters;
                    }
            }
        }
        throw new XMLStreamException("Unknown element in tag <visual-parameters>");
    }

    private void fillAttribute(AbstractGem gem, XMLStreamReader reader) throws XMLStreamException {
        gem.setName(reader.getAttributeValue(null, GemXMLTag.NAME.toString()));
        PreciousnessType defaultPreciousness = PreciousnessType.PRECIOUS;
        if(reader.hasNext()) {
            gem.setPreciousness(PreciousnessType.valueOf(reader.getAttributeValue(1).toUpperCase()));
        } else {gem.setPreciousness(defaultPreciousness);}
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
