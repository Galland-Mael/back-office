package project.backoffice.Enum;

public enum JsonTypeEnum {
    PRESETS_STANDARD("presetsStandard"),
    LIBRARY("library");

    private final String code;


    JsonTypeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
