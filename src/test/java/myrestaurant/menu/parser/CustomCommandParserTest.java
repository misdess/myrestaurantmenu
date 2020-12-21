package myrestaurant.menu.parser;

import java.io.File;

import myrestaurant.menu.ValidationResult;
import org.apache.commons.cli.ParseException;
import org.junit.Assert;
import org.junit.Test;

public class CustomCommandParserTest {
    CustomCommandParser parser = new CustomCommandParser();
    private final String filePath = new File("").getAbsolutePath().concat("/src/main/resources/sample/menu.json");
    private final String order = "asc";

    @Test
    public void testValidArguments() throws ParseException {

        String args[] = new String[2];
        args[0] = filePath;
        args[1] = order;

        ValidationResult validationResult = parser.validateArguments(args);

        Assert.assertEquals(ValidationResult.VALID, validationResult);
    }

    @Test
    public void testInvalidArguments() throws ParseException {

        String args[] = new String[2];
        args[0] = filePath.concat("wrong");
        args[1] = order;

        ValidationResult validationResult = parser.validateArguments(args);

        Assert.assertEquals(ValidationResult.INVALID, validationResult);
    }

}