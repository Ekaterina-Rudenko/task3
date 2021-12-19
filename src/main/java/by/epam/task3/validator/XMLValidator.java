package by.epam.task3.validator;

import by.epam.task3.exception.GemException;
import by.epam.task3.handler.GemsErrorHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XMLValidator {
    static Logger logger = LogManager.getLogger();


    public static boolean validateXMLFile(String fileXMLPath, String schemaPath) throws GemException {
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        File schemaFile = new File(schemaPath);
        try {
            Schema schema = factory.newSchema(schemaFile);
            Validator validator = schema.newValidator();
            Source source = new StreamSource(fileXMLPath);
            validator.setErrorHandler(new GemsErrorHandler());
            validator.validate(source);
        } catch (IOException e) {
            logger.error("Reading from " + fileXMLPath + " was failed or interrupted", e);
            throw new GemException("Reading from " + fileXMLPath + " was failed or interrupted", e);
        } catch (SAXException e) {
            logger.error("File " + fileXMLPath + " is not valid: ", e);
            return false;
        }
        return true;
    }
}
