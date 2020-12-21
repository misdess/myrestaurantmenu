package myrestaurant.menu.model;


public enum Order {

    ASC,
    DESC;

    public String value() {
        return name();
    }

    public static Order fromValue(String v) {
        return valueOf(v);
    }

}

