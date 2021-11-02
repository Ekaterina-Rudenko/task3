package by.epam.task3.builder;

import by.epam.task3.entity.*;
import by.epam.task3.entity.enums.Country;
import by.epam.task3.entity.enums.GemColour;
import by.epam.task3.entity.enums.PreciousnessType;
import by.epam.task3.exception.GemException;
import by.epam.task3.handler.GemXMLTag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Set;

public class DomGemBuilder extends AbstractGemBuilder {
    private static Logger logger = (Logger) LogManager.getLogger();
    private DocumentBuilder documentBuilder;

    @Override
    public Set<AbstractGem> getGems() {
        return super.getGems();
    }

    public DomGemBuilder() {
        super();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            logger.error("Error in Dom: " + ex.getMessage());
        }
    }

    @Override
    public void buildGems(String filename) throws GemException {
        Document doc;
        try {
            doc = documentBuilder.parse(filename);
            Element root = doc.getDocumentElement();
            NodeList naturalsList = root.getElementsByTagName("natural");
            NodeList syntheticsList = root.getElementsByTagName("synthetic");
            for (int i = 0; i < naturalsList.getLength(); i++) {
                Element gemElement = (Element) naturalsList.item(i);
                NaturalGem gem = buildNaturals(gemElement);
                gems.add(gem);
            }
            for (int i = 0; i < syntheticsList.getLength(); i++) {
                Element gemElement = (Element) syntheticsList.item(i);
                SyntheticGem gem = buildSynthetics(gemElement);
                gems.add(gem);
            }
        } catch (IOException ex) {
            logger.error("Error in Dom, check your filename: " + filename, ex);
            throw new GemException("Error in Dom, check your filename: " + filename, ex);
        } catch (SAXException ex) {
            logger.error("Error in Dom: " + ex.getMessage());
            throw new GemException("Error in Dom: " + ex.getMessage());
        }
        logger.info("Gems from dom builder are:\n" + gems);
    }

    private NaturalGem buildNaturals(Element element) {
        NaturalGem naturalGem = new NaturalGem();

        naturalGem.setOriginCountry(Country.valueOf(getElementTextContent(element, GemXMLTag.ORIGIN_COUNTRY.toString()).toUpperCase()));
        naturalGem.setExtractionDate(LocalDate.parse(getElementTextContent(element, GemXMLTag.EXTRACTION_DATE.toString())));

        build(element, naturalGem);
        return naturalGem;
    }

    private SyntheticGem buildSynthetics(Element element) {
        SyntheticGem syntheticGem = new SyntheticGem();

        Producer producer = syntheticGem.getProducer();
        Element producerElement = (Element) element.getElementsByTagName(GemXMLTag.PRODUCER.toString()).item(0);
        producer.setCompany(getElementTextContent(producerElement, GemXMLTag.COMPANY.toString()));
        producer.setCountry(Country.valueOf(getElementTextContent(producerElement, GemXMLTag.HEADQUARTER.toString()).toUpperCase()));
        syntheticGem.setDateOfProduction(LocalDate.parse(getElementTextContent(element, GemXMLTag.PRODUCTION_DATE.toString())));
        build(element, syntheticGem);
        return syntheticGem;
    }

    private void build(Element element, AbstractGem gem) {
        gem.setName(element.getAttribute(GemXMLTag.NAME.toString()));
        if (element.hasAttribute(GemXMLTag.PRECIOUSNESS.toString())) {
            gem.setPreciousness(PreciousnessType.valueOf(element.getAttribute(GemXMLTag.PRECIOUSNESS.toString()).toUpperCase()));
        } else {
            gem.setPreciousness(PreciousnessType.PRECIOUS);
        }
        gem.setGemId(getElementTextContent(element, GemXMLTag.ID.toString()));

        VisualParameters parameter = gem.getParameters();
        Element visualElement = (Element) element.getElementsByTagName(GemXMLTag.VISUAL_PARAMETERS.toString()).item(0);
        parameter.setColour(GemColour.valueOf(getElementTextContent(visualElement, GemXMLTag.COLOUR.toString()).toUpperCase()));
        parameter.setTransparency(Integer.parseInt(getElementTextContent(visualElement, GemXMLTag.TRANSPARENCY.toString())));
        parameter.setCut(Integer.parseInt(getElementTextContent(visualElement, GemXMLTag.CUT.toString())));

        gem.setValue(Double.parseDouble(getElementTextContent(element, GemXMLTag.VALUE.toString())));

    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;
    }

}
