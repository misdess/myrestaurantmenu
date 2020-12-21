package myrestaurant.menu.parser.json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.TreeMap;

import myrestaurant.menu.model.Menu;
import myrestaurant.menu.model.MenuBook;
import myrestaurant.menu.parser.FileParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonFileParser implements FileParser {

    @Override
    public TreeMap<String, Menu> parseMenuFile(String path) {

        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(path));

            JSONObject jsonObject = (JSONObject) obj;

            JSONObject breakfast_menu = getJsonObject(jsonObject, "breakfast_menu");
            JSONArray menuList = (JSONArray) breakfast_menu.get("food");

            Iterator<JSONObject> iterator = menuList.iterator();
            while (iterator.hasNext()) {
                JSONObject next = iterator.next();
                Menu food = new Menu((String) next.get("name"), (String) next.get("price"), (String) next.get("description"), Double.valueOf((String) next.get("calories")));
                MenuBook.addFood(food);
            }

        } catch (ParseException ex) {
            System.out.println((String.format("Cannot parse file: " + path)));
        } catch (NullPointerException ex) {
            System.out.println((String.format("Cannot find Json object in: " + ex.getMessage())));
        } catch (FileNotFoundException ex) {
            System.out.println((String.format("Cannot find file: " + path)));
        } catch (IOException ex) {
            System.out.println((String.format("Cannot find file {}", ex.getMessage())));
        }

        return MenuBook.getMenuBook();
    }

    private JSONObject getJsonObject(JSONObject jsonObject, String name) throws NullPointerException {
        JSONObject breakfastMenu = null;
        try {
            breakfastMenu = (JSONObject) jsonObject.get(name);
        } catch (Exception e) {
            throw new NullPointerException(name);
        }
        return breakfastMenu;

    }
}
