package myrestaurant.menu.parser.xml;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.TreeMap;

import generated.BreakfastMenu;
import generated.ObjectFactory;
import myrestaurant.menu.model.Menu;
import myrestaurant.menu.model.MenuBook;
import myrestaurant.menu.parser.FileParser;
import org.xml.sax.SAXException;


public class XmlFileParser implements FileParser {

    private final String SCHEMA_FILE = "schema/menu.xsd";

    @Override
    public TreeMap<String, Menu> parseMenuFile(String path) {

        File file = new File(path);
        InputStream schema = null;
        try {
            schema = getInputStream(SCHEMA_FILE);
        } catch (FileNotFoundException ex) {
            System.out.println(String.format("File {} does not exist {}", SCHEMA_FILE, ex));
        }

        BreakfastMenu breakfastMenu = createBreakfastMenu(file, schema);
        breakfastMenu.getFood().stream().forEach(food -> MenuBook.addFood(food));

        return MenuBook.getMenuBook();
    }

    private BreakfastMenu createBreakfastMenu(File xml, InputStream schema) {

        BreakfastMenu breakfastMenu = new BreakfastMenu();
        if (validateXML(xml, schema)) {
            JAXBContext jaxbContext = null;
            try {

                jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
                Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
                breakfastMenu = (BreakfastMenu) unmarshaller.unmarshal(xml);
            } catch (JAXBException ex) {
                System.out.println(String.format("There is wsdl exception {}", ex));
            }
        }

        return breakfastMenu;
    }

    public InputStream getInputStream(String configFileName) throws FileNotFoundException {
        InputStream input;
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        input = loader.getResourceAsStream(configFileName);
        if (input == null) {
            throw new FileNotFoundException(configFileName);
        }
        return input;
    }

    private boolean validateXML(File xml, InputStream xcd) {

        boolean validXML = false;
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new StreamSource(xcd));

            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xml));
            validXML = true;
        } catch (SAXException ex) {
            System.out.println((String.format("Exception occurred while validating input xml {}", ex)));
        } catch (IOException ex) {
            System.out.println(String.format("There is exception {}", ex.getMessage()));
        }

        return validXML;
    }


}
