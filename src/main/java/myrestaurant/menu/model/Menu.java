package myrestaurant.menu.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Menu {
    private String name;
    private String price;
    private String description;
    private double calories;
}
