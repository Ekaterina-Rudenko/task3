package by.epam.task3.builder;

import by.epam.task3.entity.AbstractGem;
import by.epam.task3.entity.NaturalGem;
import by.epam.task3.entity.SyntheticGem;
import by.epam.task3.entity.VisualParameters;
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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;

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
    public void buildGems(String filePath) throws GemException {
        XMLStreamReader reader;
        String name;
        try (FileInputStream inputStream = new FileInputStream(new File(filePath))) {
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
        } catch (XMLStreamException | FileNotFoundException ex) {
            logger.log(Level.ERROR, "Error in Stax: " + ex.getMessage());
            throw new GemException("Error in Stax: " + ex.getMessage());
        } catch (IOException ex) {
            logger.log(Level.ERROR, "Error in Stax, check your filename: " + filePath);
            throw new GemException("Error in Stax, check your filename: " + filePath);
        }
        logger.log(Level.INFO, "Minerals from stax builder are:\n" + gems);
    }

    private NaturalGem buildNatural(XMLStreamReader reader) throws XMLStreamException {
        NaturalGem naturalGem = new NaturalGem();
        build(reader, naturalGem);
        naturalGem.setOriginCountry(Country.valueOf(getXMLText(reader).toUpperCase()));
        naturalGem.setExtractionDate(LocalDate.parse(getXMLText(reader)));
        return naturalGem;
    }

    private SyntheticGem buildSynthetic(XMLStreamReader reader) throws XMLStreamException {
        SyntheticGem syntheticGem = new SyntheticGem();
        build(reader, syntheticGem);
        syntheticGem.getProducer().setCompany(getXMLText(reader));
        syntheticGem.getProducer().setCountry(Country.valueOf(getXMLText(reader).toUpperCase()));
        syntheticGem.setDateOfProduction(LocalDate.parse(getXMLText(reader)));
        return syntheticGem;
    }

    private void build(XMLStreamReader reader, AbstractGem gem) throws XMLStreamException {
        gem.setName(reader.getAttributeValue(null, GemXMLTag.NAME.toString()));
        if (reader.getAttributeValue(1) != null) {
            gem.setPreciousness(PreciousnessType.valueOf(reader.getAttributeValue(null, GemXMLTag.PRECIOUSNESS.toString()).toUpperCase()));
        } else {
            gem.setPreciousness(PreciousnessType.PRECIOUS);
        }
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (GemXMLTag.valueOf(name.toUpperCase().replace(HYPHEN, UNDERSCORE))) {
                        case VISUAL_PARAMETERS:
                            gem.setParameters(getParameters(reader));
                            break;
                        case VALUE:
                            gem.setValue(Double.parseDouble(getXMLText(reader)));
                            break;
                        case ID:
                            gem.setGemId(getXMLText(reader));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (GemXMLTag.valueOf(name.toUpperCase().replace(HYPHEN, UNDERSCORE)) == GemXMLTag.NATURAL ||
                            GemXMLTag.valueOf(name.toUpperCase().replace(HYPHEN, UNDERSCORE)) == GemXMLTag.SYNTHETIC) {
                        return;
                    }
            }
        }
        throw new XMLStreamException("Unknown element in tag <precious>");
    }

    private VisualParameters getParameters(XMLStreamReader reader) throws XMLStreamException {
        VisualParameters parameters = new VisualParameters();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (GemXMLTag.valueOf(name.toUpperCase().replace(HYPHEN, UNDERSCORE))) {
                        case COLOUR:
                            parameters.setColour(GemColour.valueOf(getXMLText(reader).toUpperCase()));
                            break;
                        case TRANSPARENCY:
                            parameters.setTransparency(Integer.parseInt(getXMLText(reader)));
                            break;
                        case CUT:
                            parameters.setCut(Integer.parseInt(getXMLText(reader)));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (GemXMLTag.valueOf(name.toUpperCase().replace(HYPHEN, UNDERSCORE)) == GemXMLTag.VISUAL_PARAMETERS) {
                        return parameters;
                    }
            }
        }
        throw new XMLStreamException("Unknown element in tag <visual-parameters>");
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
