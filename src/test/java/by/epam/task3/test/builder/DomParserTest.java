package by.epam.task3.test.builder;

import by.epam.task3.builder.AbstractGemBuilder;
import by.epam.task3.builder.BuilderFactory;
import by.epam.task3.entity.AbstractGem;
import by.epam.task3.exception.GemException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URL;

public class DomParserTest {
    AbstractGemBuilder builder;
    static final String TEST_XML_PATH = "tesdata/gems.xml";
    String filePath;

    @BeforeClass
    public void create() throws GemException {
        ClassLoader loader = DomParserTest.class.getClassLoader();
        URL resourceXML = loader.getResource(TEST_XML_PATH);
        filePath = new File(resourceXML.getFile()).getAbsolutePath();
        builder = BuilderFactory.createGemsBuilder("dom");
    }

    @Test
    public void testParsingNames() throws GemException {
        String[] actual = new String[]{"ruby", "diamond", "sapphire", "emerald", "tourmaline",
                "opal", "beryl", "alexandrite", "amethyst", "diamond", "diamond", "ruby",
                "emerald", "sapphire", "opal", "diamond"};
        builder.buildGems(filePath);
        Object[] expected = builder.getGems().stream()
                .map(AbstractGem::getName)
                .toArray();
        Assert.assertEqualsNoOrder(actual, expected);
    }

    @Test(expectedExceptions = GemException.class)
    public void testException() throws GemException {
        builder.buildGems("");
    }
}
