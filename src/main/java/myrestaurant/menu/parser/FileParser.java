package myrestaurant.menu.parser;

import java.util.TreeMap;

import myrestaurant.menu.model.Menu;


public interface FileParser {

    public TreeMap<String, Menu> parseFileAndPopulateMenuBook(String path);

}