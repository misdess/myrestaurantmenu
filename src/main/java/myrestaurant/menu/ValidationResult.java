package myrestaurant.menu;

public enum ValidationResult {
    VALID,
    INVALID;


    public String value() {
        return name();
    }

    public static ValidationResult fromValue(String v) {
        return valueOf(v);
    }

}
