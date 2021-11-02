package by.epam.task3.main;

import by.epam.task3.builder.DomGemBuilder;
import by.epam.task3.builder.SaxGemBuilder;
import by.epam.task3.builder.StaxGemBuilder;
import by.epam.task3.exception.GemException;
import by.epam.task3.validator.XMLValidator;

import java.io.File;
import java.net.URL;

public class Main {
    static final String FILE_XML_PATH = "data/gems.xml";

    public static void main(String[] args) throws GemException {
        ClassLoader loader = Main.class.getClassLoader();
        URL resource = loader.getResource(FILE_XML_PATH);
        String filePath = new File(resource.getFile()).getAbsolutePath();
        XMLValidator.validateXMLFile(filePath);

       /* DomGemBuilder domBuilder = new DomGemBuilder();
        domBuilder.buildGems(filePath);
        System.out.println(domBuilder.getGems());


        SaxGemBuilder saxBuilder = new SaxGemBuilder();
        saxBuilder.buildGems(filePath);
        System.out.println(saxBuilder.getGems());*/

        StaxGemBuilder staxGemBuilder = new StaxGemBuilder();
        staxGemBuilder.buildGems(filePath);
        System.out.println(saxBuilder.getGems());
    }
}
