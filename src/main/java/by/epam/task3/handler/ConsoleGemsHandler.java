package by.epam.task3.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class ConsoleGemsHandler extends DefaultHandler {
    static Logger logger = LogManager.getLogger();
    @Override
    public void startDocument(){
        System.out.println("Parsing started");
    }
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attr){
        String tagData = qName + " ";
        for(int i = 0; i < attr.getLength(); i++){
            tagData += " " + attr.getQName(i) + "=" + attr.getValue(i);
        }
        System.out.print(tagData);
    }

    @Override
    public void characters(char[] ch, int start, int length){
        System.out.print(new String(ch, start, length));
    }
    @Override
    public void endElement(String uri, String localName, String qName){
        System.out.print(" " + qName);
    }
    @Override
    public void endDocument(){
        System.out.println("\nParsing ended");
    }

}
