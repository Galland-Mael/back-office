package project.backoffice.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
public class ApiFinalException {
    private final String messageExceptionEnum;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timestamp;
}