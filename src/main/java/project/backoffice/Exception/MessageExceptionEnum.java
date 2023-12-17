package project.backoffice.Exception;

import lombok.Getter;

@Getter
public enum MessageExceptionEnum {

    // USER
    USER_NOT_FOUND("User not found for id: %s"),
    // AUTHENTICATION
    LOGIN_OR_PASSWORD_INCORRECT("Login or password incorrect"),
    LIBRARY_NOT_FOUND("Library not found for id: %s"),
    LIBRARY_ALREADY_EXISTS_FOR_USER("Library already exists for user id: %s");

    private final String message;

    MessageExceptionEnum(String message) {
        this.message = message;
    }

}
