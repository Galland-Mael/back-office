package project.backoffice.exception;

import lombok.Getter;

@Getter
public enum MessageExceptionEnum {

    // AUTHENTICATION
    LOGIN_OR_PASSWORD_INCORRECT("Login or password incorrect"),
    USER_NOT_FOUND("User not found for id: %s"),
    LIBRARY_NOT_FOUND("Library not found for id: %s"),
    LIBRARY_ALREADY_EXISTS_FOR_USER("Library already exists for user id: %s"),
    JSON_PARSING_ERROR("Error parsing json");

    private final String message;

    MessageExceptionEnum(String message) {
        this.message = message;
    }

}
