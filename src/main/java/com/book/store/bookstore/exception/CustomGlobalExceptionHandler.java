package com.book.store.bookstore.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        return new ResponseEntity<>(
            getResponseBody(
                HttpStatus.BAD_REQUEST,
                ex.getBindingResult().getAllErrors()
                    .stream()
                    .map(this::getErrorMessage)
                    .toList()
            ),
            headers,
            status
        );
    }

    @ExceptionHandler(value = IncorrectSpecificationKeyException.class)
    public ResponseEntity<Object> handleIncorrectSpecificationKeyException(
        IncorrectSpecificationKeyException ex
    ) {
        return new ResponseEntity<>(
            getResponseBody(HttpStatus.BAD_REQUEST, List.of(ex.getMessage())),
            HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(value = RegistrationException.class)
    public ResponseEntity<Object> handleRegistrationException(RegistrationException ex) {
        return new ResponseEntity<>(
            getResponseBody(HttpStatus.BAD_REQUEST, List.of(ex.getMessage())),
            HttpStatus.BAD_REQUEST
        );
    }

    private Map<String, Object> getResponseBody(HttpStatus httpStatus, List<String> errors) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", httpStatus);
        body.put("error", errors);
        return body;
    }

    private String getErrorMessage(ObjectError error) {
        if (!(error instanceof FieldError)) {
            return error.getDefaultMessage();
        }

        String field = ((FieldError) error).getField();
        String message = error.getDefaultMessage();
        return field + " " + message;
    }
}
