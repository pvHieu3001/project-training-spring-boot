package com.smartosc.training.exceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Fresher-Training
 *
 * @author Namtt
 * @created_at 02/07/2020 - 10:48 AM
 * @created_by Namtt
 * @update_by Thanhttt
 * @since 02/07/2020
 */
@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {

        Map<String, String> errorMessageMap = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(e -> {
            if (e instanceof FieldError) {
                errorMessageMap.put(((FieldError) e).getField(), e.getDefaultMessage());
            }
        });
        ErrorObject errorObject = new ErrorObject();
        errorObject.setMessages(errorMessageMap);

        return new ResponseEntity<>(errorObject, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorObject> customHandleNotFound(Exception ex, WebRequest request, Locale locale) {
        ErrorObject errorObject = new ErrorObject();
        errorObject.setTimestamp(LocalDateTime.now());
        errorObject.setError(messageSource.getMessage(ex.getMessage(), null, locale));
        errorObject.setStatus(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorObject, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(LockedException.class)
    public ResponseEntity<ErrorObject> customHandleLocked(Exception ex, WebRequest request, Locale locale) {
        ErrorObject errorObject = new ErrorObject();
        errorObject.setTimestamp(LocalDateTime.now());
        errorObject.setError(messageSource.getMessage(ex.getMessage(), null, locale));
        errorObject.setStatus(HttpStatus.LOCKED.value());
        return new ResponseEntity<>(errorObject, HttpStatus.LOCKED);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorObject> customHandleNullPointer(Exception ex, WebRequest request, Locale locale) {
        ErrorObject errorObject = new ErrorObject();
        errorObject.setTimestamp(LocalDateTime.now());
        errorObject.setError(messageSource.getMessage(ex.getMessage(), null, locale));
        errorObject.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorObject, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorObject> customHandleOtherError(Exception ex, WebRequest request, Locale locale) {
        ErrorObject errorObject = new ErrorObject();
        errorObject.setTimestamp(LocalDateTime.now());
        errorObject.setError(messageSource.getMessage(ex.getMessage(), null, locale));
        errorObject.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorObject, HttpStatus.BAD_REQUEST);
    }
}
