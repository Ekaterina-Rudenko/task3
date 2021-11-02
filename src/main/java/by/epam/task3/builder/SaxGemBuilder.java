package by.epam.task3.builder;

import by.epam.task3.entity.AbstractGem;
import by.epam.task3.handler.GemHandler;
import by.epam.task3.handler.GemsErrorHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.Set;

public class SaxGemBuilder extends AbstractGemBuilder {
    static Logger logger = LogManager.getLogger();
    private GemHandler handler;
    private XMLReader reader;

    public SaxGemBuilder() {
        handler = new GemHandler();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            reader = saxParser.getXMLReader();
        } catch (ParserConfigurationException | SAXException e) {
            logger.error("TO DO Exception in SAXBuilder");
        }
        reader.setContentHandler(handler);
        reader.setErrorHandler(new GemsErrorHandler());
    }
    public SaxGemBuilder (Set<AbstractGem> gems){
        super(gems);
    }

    @Override
    public void buildGems(String fileName) {
        try {
            reader.parse(fileName);
        } catch (IOException | SAXException e) {
            logger.error("TO DO built gems exception ");
        }
        gems = handler.getGems();
    }
}
