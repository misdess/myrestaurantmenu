package myrestaurant.menu;

import myrestaurant.menu.model.MenuBook;
import myrestaurant.menu.parser.CustomCommandParser;
import myrestaurant.menu.parser.FileParser;
import myrestaurant.menu.parser.json.JsonFileParser;
import myrestaurant.menu.parser.xml.XmlFileParser;
import org.apache.commons.cli.ParseException;
import org.apache.commons.io.FilenameUtils;


public class Application {

    private static final String JSON = "json";


    public static void main(String[] args) throws ParseException {
        CustomCommandParser customCommandParser = new CustomCommandParser();
        ValidationResult validationResult = customCommandParser.validateArguments(args);

        if (ValidationResult.INVALID.equals(validationResult)) {
            //Argument is invalid and help message is displayed
            return;
        }

        MenuBook.createInstance(args[1]);

        FileParser fileParser = getFileParser(args);
        fileParser.parseMenuFile(args[0]);

        MenuBook.printMenu();
    }

    private static FileParser getFileParser(String[] args) {

        String ext = FilenameUtils.getExtension(args[0]);
        if (JSON.equalsIgnoreCase(ext)) {
            return new JsonFileParser();
        } else {
            return new XmlFileParser();
        }

    }

}
