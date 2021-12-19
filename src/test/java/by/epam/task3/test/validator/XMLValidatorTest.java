package by.epam.task3.test.validator;

import by.epam.task3.exception.GemException;
import by.epam.task3.validator.XMLValidator;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URL;

public class XMLValidatorTest {

    static final String TEST_XML_PATH = "testdata/gems.xml";
    static final String TEST_XSD_PATH = "testdata/gems.xsd";
    static final String TEST_WRONG_XML_PATH = "testdata/wrong.xml";
    static final String TEST_WRONG_XSD_PATH = "testdata/wrong.xsd";
    String filePath;
    String schemaPath;
    String wrongXml;
    String wrongXsd;
    @BeforeClass
    void setUp(){
        ClassLoader loader = XMLValidatorTest.class.getClassLoader();
        URL resourceXML = loader.getResource(TEST_XML_PATH);
        filePath = new File(resourceXML.getFile()).getAbsolutePath();
        URL resourceXSD = loader.getResource(TEST_XSD_PATH);
        schemaPath = new File(resourceXSD.getFile()).getAbsolutePath();
        URL resourceWrongXml = loader.getResource(TEST_WRONG_XML_PATH);
        wrongXml = new File(resourceXSD.getFile()).getAbsolutePath();
        URL resourceWrongXsd = loader.getResource(TEST_WRONG_XSD_PATH);
        wrongXsd = new File(resourceXSD.getFile()).getAbsolutePath();
    }

    @Test
    void validateXmlFileTest() throws GemException {
        boolean actual = XMLValidator.validateXMLFile(filePath, schemaPath);
        Assert.assertTrue(actual);
    }

    @Test(expectedExceptions = GemException.class)
    void validateXmlFileExceptionTest() throws GemException {
        boolean actual = XMLValidator.validateXMLFile("fileWrong.xml", schemaPath);
    }


}
