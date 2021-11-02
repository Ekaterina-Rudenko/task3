package by.epam.task3.builder;

import by.epam.task3.entity.AbstractGem;
import by.epam.task3.exception.GemException;
import by.epam.task3.handler.GemHandler;
import by.epam.task3.handler.GemsErrorHandler;
import org.apache.logging.log4j.Level;
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

    public SaxGemBuilder() throws GemException {
        handler = new GemHandler();
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            reader = saxParser.getXMLReader();
        } catch (ParserConfigurationException | SAXException e) {
            logger.error("Exception in SAXBuilder", e);
            throw new GemException("Exception in SAX Builder ", e);
        }
        reader.setContentHandler(handler);
        reader.setErrorHandler(new GemsErrorHandler());
    }
    public SaxGemBuilder (Set<AbstractGem> gems){
        super(gems);
    }

    @Override
    public void buildGems(String fileName) throws GemException {
        try {
            reader.parse(fileName);
        } catch (IOException | SAXException e) {
            logger.error("Exception in Sax Parser " , e);
            throw new GemException("Exception in Sax Parser", e);
        }
        logger.log(Level.INFO, "Gems from SAX builder are:" );
        gems = handler.getGems();
    }
}
