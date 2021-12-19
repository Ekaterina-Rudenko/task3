package by.epam.task3.main;

import by.epam.task3.builder.*;
import by.epam.task3.exception.GemException;
import by.epam.task3.validator.XMLValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.net.URL;

public class Main {
    static Logger logger = LogManager.getLogger();
    static final String FILE_XML_PATH = "data/gems.xml";
    static final String SCHEMA_NAME = "data/gems.xsd";
    static final String TYPE_OF_PARSER_SAX = "Sax";
    static final String TYPE_OF_PARSER_STAX = "Stax";
    static final String TYPE_OF_PARSER_DOM = "Dom";

    public static void main(String[] args) throws GemException {
        try {
            ClassLoader loader = Main.class.getClassLoader();
            URL resourceXML = loader.getResource(FILE_XML_PATH);
            String filePath = new File(resourceXML.getFile()).getAbsolutePath();
            URL resourceXSD = loader.getResource(SCHEMA_NAME);
            String schemaPath = new File(resourceXSD.getFile()).getAbsolutePath();
            XMLValidator.validateXMLFile(filePath, schemaPath);
            AbstractGemBuilder builder = BuilderFactory.createGemsBuilder(TYPE_OF_PARSER_DOM);
            builder.buildGems(filePath);
            logger.info(builder.getGems());
        } catch (GemException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
