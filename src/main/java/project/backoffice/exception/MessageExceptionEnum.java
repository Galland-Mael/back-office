package project.backoffice.exception;

import lombok.Getter;

@Getter
public enum MessageExceptionEnum {

    // USER
    USER_NOT_FOUND("User not found"),
    // AUTHENTICATION
    LOGIN_OR_PASSWORD_INCORRECT("Login or password incorrect");

    private final String message;

    MessageExceptionEnum(String message) {
        this.message = message;
    }

}
