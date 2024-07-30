package com.arduino.shop_api.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class ValidationException extends RuntimeException{
    private List<String> errors;

    public ValidationException(String message, List<String> errors) {
        super(message);

        this.errors = errors;
    }
}
