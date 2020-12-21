package myrestaurant.menu.model;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import generated.BreakfastMenu;

public class MenuBook {
    private static String IDENT = "      ";

    //There exists only one menu book instance
    private static TreeMap<String, Menu> menuBook = null;

    public static void createInstance(String order) {
        if (menuBook != null) {
            return;
        }
        if (Order.ASC.equals(Order.valueOf(order.toUpperCase()))) {
            menuBook = new TreeMap<>();
        } else {
            menuBook = new TreeMap<>(Collections.reverseOrder());
        }
    }

    public static void addFood(Menu food) {
        menuBook.put(food.getName(), food);
    }

    public static void addFood(BreakfastMenu.Food food) {
        addFood(new Menu(food.getName(), food.getPrice(), food.getDescription(), food.getCalories()));
    }

    public static TreeMap<String, Menu> getMenuBook() {
        return menuBook;
    }

    public static void printMenu() {
        // Display the Menu which is naturally sorted
        for (Map.Entry<String, Menu> entry : menuBook.entrySet()) {
            System.out.println("name = " + entry.getKey());
            System.out.println(IDENT + entry.getValue().getDescription());
            System.out.println(IDENT + entry.getValue().getCalories());
            System.out.println(IDENT + entry.getValue().getPrice());
        }
    }

}
