package myrestaurant.menu.parser.xml;


import java.io.File;
import java.util.TreeMap;

import myrestaurant.menu.model.MenuBook;
import org.junit.Assert;
import org.junit.Test;
import myrestaurant.menu.model.Menu;

public class XmlFileParserTest {

    private final String FIRST_ENTRY = "Belgian Waffles";
    private final String LAST_ENTRY = "Strawberry Belgian Waffles";
    private final XmlFileParser xmlFileParser = new XmlFileParser();

    @Test
    public void testMenuInAscendingOrder() {
        String path = new File("").getAbsolutePath().concat("/src/main/resources/sample/menu.xml");
        MenuBook.createInstance( "asc");

        TreeMap<String, Menu> menuBook = xmlFileParser.parseFileAndPopulateMenuBook(path);
        Assert.assertEquals(5, menuBook.keySet().size());
        Assert.assertEquals(FIRST_ENTRY, menuBook.firstKey());
        Assert.assertEquals(LAST_ENTRY, menuBook.lastKey());
    }

}