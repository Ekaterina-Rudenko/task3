package by.epam.task3.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class ConsoleGemsHandler extends DefaultHandler {
    static Logger logger = LogManager.getLogger();

    @Override
    public void startDocument() {
        logger.info("Parsing started");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attr) {
        String tagData = qName + " ";
        for (int i = 0; i < attr.getLength(); i++) {
            tagData += " " + attr.getQName(i) + "=" + attr.getValue(i);
        }
        logger.info(tagData);
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        logger.info(new String(ch, start, length));
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        logger.info(" " + qName);
    }

    @Override
    public void endDocument() {
        logger.info("\nParsing ended");
    }

}
