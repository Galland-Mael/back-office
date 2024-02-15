package project.backoffice.exception;

import lombok.Getter;

@Getter
public enum MessageExceptionEnum {

    // AUTHENTICATION
    LOGIN_OR_PASSWORD_INCORRECT("Login or password incorrect"),
    USER_NOT_FOUND("User not found for id: %s"),
    USER_ALREADY_EXISTS("User already exists for email: %s"),
    RESET_PASSWORD_TOKEN_INVALID("Reset password token invalid"),
    LIBRARY_NOT_FOUND("Library not found for id: %s"),
    LIBRARY_ALREADY_EXISTS_FOR_USER("Library already exists for user id: %s"),
    JSON_PARSING_ERROR("Error parsing json"),
    QUALITY_NOT_FOUND("Quality not found for id: %s"),
    JSON_TYPE_NOT_FOUND("Json type not found"),
    FIRMWARE_NOT_FOUND("Firmware not found for id: %s");
    private final String message;

    MessageExceptionEnum(String message) {
        this.message = message;
    }

}
