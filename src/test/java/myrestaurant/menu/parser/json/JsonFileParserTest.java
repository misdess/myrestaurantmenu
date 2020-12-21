package myrestaurant.menu.parser.json;

import java.io.File;
import java.util.TreeMap;

import myrestaurant.menu.model.Menu;
import myrestaurant.menu.model.MenuBook;
import org.junit.Assert;
import org.junit.Test;

public class JsonFileParserTest {

    private final String FIRST_ENTRY = "Belgian Waffles";
    private final String LAST_ENTRY = "Strawberry Belgian Waffles";
    private final JsonFileParser jsonFileParser = new JsonFileParser();

    @Test
    public void testMenuInAscendingOrder() {
        String filePath = new File("").getAbsolutePath().concat("/src/test/resources/sample/menu.json");
        MenuBook.createInstance("asc");

        TreeMap<String, Menu> menuBook = jsonFileParser.parseMenuFile(filePath);
        Assert.assertEquals(5, menuBook.keySet().size());
        Assert.assertEquals(FIRST_ENTRY, menuBook.firstKey());
        Assert.assertEquals(LAST_ENTRY, menuBook.lastKey());
    }

}