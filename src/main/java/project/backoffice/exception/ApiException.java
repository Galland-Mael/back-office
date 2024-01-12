package project.backoffice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiException extends RuntimeException{

    private final HttpStatus httpStatus;
    public ApiException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public ApiException(HttpStatus httpStatus, MessageExceptionEnum message) {
        super(message.getMessage());
        this.httpStatus = httpStatus;
    }
}
