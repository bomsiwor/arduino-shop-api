package com.arduino.shop_api.exception;

import com.arduino.shop_api.model.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException e) {
        ErrorResponse error = this.generateError(e, ErrorMessage.DATA_NOT_FOUND, null);

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(ValidationException e) {
        ErrorResponse error = this.generateError(e, e.getMessage(), e.getErrors());

        return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    private ErrorResponse generateError(RuntimeException e, String reasonPhrase, List<String> subErrors) {
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .error(reasonPhrase)
                .message(e.getMessage())
                .subErrors(subErrors)
                .build();
    }
}
