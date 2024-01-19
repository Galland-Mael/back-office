package project.backoffice.enumeration;

public enum SortByEnum {

    EMAIL("email"),
    LAST_NAME("lastName"),
    FIRST_NAME("firstName"),
    PHONE("phone"),
    QUALITY_NAME("quality.name");

    private final String code;


    SortByEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static SortByEnum findByCode(String code) {
        for (SortByEnum b : SortByEnum.values()) {
            if (b.code.equalsIgnoreCase(code.toLowerCase())) {
                return b;
            }
        }
        return null;
    }
}
