package project.backoffice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleApiException(Exception e){
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        if(e instanceof ApiException){
            httpStatus = ((ApiException) e).getHttpStatus();
        }
        ApiFinalException apiFinalException = new ApiFinalException(
                e.getMessage(),
                httpStatus,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiFinalException, httpStatus);
    }
}
