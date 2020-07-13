package com.smartosc.training.exceptions;

import com.smartosc.training.dto.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DuplicateKeyException;
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

  @Autowired private MessageSource messageSource;

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {

    Map<String, String> errorMessageMap = new HashMap<>();

    ex.getBindingResult()
        .getAllErrors()
        .forEach(
            e -> {
              if (e instanceof FieldError) {
                errorMessageMap.put(
                    ((FieldError) e).getField(),
                    messageSource.getMessage(
                        e.getDefaultMessage(), null, request.getLocale()));
              }
            });

    ErrorResponse errorResponse = new ErrorResponse();

    errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
    errorResponse.setTimestamp(LocalDateTime.now());
    errorResponse.setMessages(errorMessageMap);

    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ErrorResponse> customHandleNotFound(
      NotFoundException ex, WebRequest request, Locale locale) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setTimestamp(LocalDateTime.now());
    errorResponse.setError(messageSource.getMessage(ex.getMessage(), null, locale));
    errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(DuplicateKeyException.class)
  public ResponseEntity<ErrorResponse> customHandleDuplicate(
      DuplicateKeyException ex, WebRequest request, Locale locale) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setTimestamp(LocalDateTime.now());
    errorResponse.setError(messageSource.getMessage(ex.getMessage(), null, locale));
    errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }
}
