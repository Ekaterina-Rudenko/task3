package by.epam.task3.handler;

import by.epam.task3.entity.*;
import by.epam.task3.entity.enums.Country;
import by.epam.task3.entity.enums.GemColour;
import by.epam.task3.entity.enums.PreciousnessType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import static by.epam.task3.handler.GemXMLTag.*;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class GemHandler extends DefaultHandler {
    static Logger logger = LogManager.getLogger();
    private static final String DELIMITER_HYPHEN = "-";
    private static final String DELIMITER_UNDERSCORE = "_";

    private Set<AbstractGem> gems;
    private AbstractGem currentGem;
    private GemXMLTag currentXmlTag;
    private EnumSet<GemXMLTag> withText;


    public GemHandler(){
        gems = new HashSet<AbstractGem>();
        withText = EnumSet.range(ID, PRODUCTION_DATE);
    }

    public Set<AbstractGem> getGems() {
        return gems;
    }

    @Override
    public void startDocument() {
        logger.info("Gems SAX handler starts working");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        String naturalGemTag = GemXMLTag.NATURAL.toString();
        String syntheticGemTag = GemXMLTag.SYNTHETIC.toString();

        if (naturalGemTag.equals(qName) || syntheticGemTag.equals(qName)) {
            if (naturalGemTag.equals(qName)) {
                currentGem = new NaturalGem();
            } else {
                currentGem = new SyntheticGem();
            }
            for (int i = 0; i < attrs.getLength(); i++) {
                String constantName = toConstantName(attrs.getQName(i));
                currentXmlTag = GemXMLTag.valueOf(constantName);
                switch (currentXmlTag) {
                    case NAME:
                        currentGem.setName(attrs.getValue(i));
                        break;
                    case PRECIOUSNESS:
                        currentGem.setPreciousness(PreciousnessType.valueOf((attrs.getValue(i)).toUpperCase()));
                        break;
                    default:
                }
            }
            if (attrs.getLength() == 1) {
                currentGem.setPreciousness(PreciousnessType.PRECIOUS);
            }
            currentXmlTag = null;
        } else {
            GemXMLTag temp = GemXMLTag.valueOf(toConstantName(qName));
            if (withText.contains(temp)) {
                currentXmlTag = temp;
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        String naturalGemTag = GemXMLTag.NATURAL.toString();
        String syntheticGemTag = GemXMLTag.SYNTHETIC.toString();
        if (naturalGemTag.equals(qName) || syntheticGemTag.equals(qName)) {
            gems.add(currentGem);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String currentData = new String(ch, start, length).strip();
        if (currentXmlTag != null) {
            switch (currentXmlTag) {
                case ID:
                    currentGem.setGemId(currentData);
                    break;
                case COLOUR:
                    currentGem.getParameters().setColour(GemColour.valueOf(toConstantName(currentData)));
                    break;
                case TRANSPARENCY:
                    currentGem.getParameters().setTransparency(Integer.parseInt(currentData));
                    break;
                case CUT:
                    currentGem.getParameters().setCut(Integer.parseInt(currentData));
                    break;
                case VALUE:
                    currentGem.setValue(Double.parseDouble(currentData));
                    break;
                case ORIGIN_COUNTRY: {
                    NaturalGem naturalGem = (NaturalGem) currentGem;
                    naturalGem.setOriginCountry(Country.valueOf(toConstantName(currentData)));
                    break;
                }
                case EXTRACTION_DATE: {
                    NaturalGem naturalGem = (NaturalGem) currentGem;
                    naturalGem.setExtractionDate(LocalDate.parse(currentData));
                    break;
                }
                case VISUAL_PARAMETERS:
                    break;
                case COMPANY: {
                    SyntheticGem syntheticGem = (SyntheticGem) currentGem;
                    syntheticGem.getProducer().setCompany(currentData);
                    break;
                }
                case HEADQUARTER: {
                    SyntheticGem syntheticGem = (SyntheticGem) currentGem;
                    syntheticGem.getProducer().setCountry(Country.valueOf(toConstantName(currentData)));
                    break;
                }
                case PRODUCTION_DATE: {
                    SyntheticGem syntheticGem = (SyntheticGem) currentGem;
                    syntheticGem.setDateOfProduction(LocalDate.parse(currentData));
                    break;
                }
                default:
                    throw new EnumConstantNotPresentException(
                            currentXmlTag.getDeclaringClass(), currentXmlTag.name());
            }
        }
        currentXmlTag = null;
    }

    private String toConstantName(String name) {
        return name.replace(DELIMITER_HYPHEN, DELIMITER_UNDERSCORE).toUpperCase();
    }
}
