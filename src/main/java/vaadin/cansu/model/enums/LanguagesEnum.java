package vaadin.cansu.model.enums;

public enum LanguagesEnum {

    TURKISH(1, "Türkçe"),
    GERMAN(2, "Almanca"),
    ITALIAN(3, "İtalyanca"),
    PORTUGUESE(5, "Portekizce");

    private final Integer id;
    private final String value;

    LanguagesEnum(Integer id, String value) {
        this.id = id;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.name();
    }
}
