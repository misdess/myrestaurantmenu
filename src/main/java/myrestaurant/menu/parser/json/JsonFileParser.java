package myrestaurant.menu.parser.json;

import java.io.FileReader;
import java.util.Iterator;
import java.util.TreeMap;

import myrestaurant.menu.model.Menu;
import myrestaurant.menu.model.MenuBook;
import myrestaurant.menu.parser.FileParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonFileParser implements FileParser {

    @Override
    public TreeMap<String, Menu> parseFileAndPopulateMenuBook(String path) {

        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(path));

            JSONObject jsonObject = (JSONObject) obj;

            JSONObject breakfast_menu = (JSONObject) jsonObject.get("breakfast_menu");
            JSONArray menuList = (JSONArray) breakfast_menu.get("food");

            Iterator<JSONObject> iterator = menuList.iterator();
            while (iterator.hasNext()) {
                JSONObject next = iterator.next();
                Menu food = new Menu((String) next.get("name"), (String) next.get("price"), (String) next.get("description"), Double.valueOf((String) next.get("calories")));
                MenuBook.addFood(food);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return MenuBook.getMenuBook();
    }

}
