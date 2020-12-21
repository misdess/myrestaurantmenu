package myrestaurant.menu.parser;

import java.io.File;

import myrestaurant.menu.ValidationResult;
import org.apache.commons.cli.ParseException;
import org.junit.Assert;
import org.junit.Test;

public class CustomCommandParserTest {

    CustomCommandParser parser = new CustomCommandParser();

    String args[] = new String[2];

    @Test
    public void testArgumentsAreValid() throws ParseException {
        args[0] = new File("").getAbsolutePath().concat("/src/main/resources/sample/menu.json");
        args[1] = "asc";

        ValidationResult validationResult = parser.validateArguments(args);

        Assert.assertEquals(ValidationResult.VALID, validationResult);
    }

    @Test
    public void testArgumentsAreInvalidForFileWithoutExtension() throws ParseException {
        args[0] = new File("").getAbsolutePath().concat("/src/main/resources/sample/menu");
        args[1] = "asc";

        ValidationResult validationResult = parser.validateArguments(args);

        Assert.assertEquals(ValidationResult.INVALID, validationResult);
    }

    @Test
    public void testArgumentsAreInvalidForWrongOrder() throws ParseException {
        args[0] = new File("").getAbsolutePath().concat("/src/main/resources/sample/menu.json");
        args[1] = "ascending";

        ValidationResult validationResult = parser.validateArguments(args);

        Assert.assertEquals(ValidationResult.INVALID, validationResult);
    }

}