package com.arduino.shop_api.model.response;

import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

public class ResponseGenerator {

    /**
     * Generate Response using custom message and MANUALLY define status code
     * @param data Data to return
     * @param code Status code (currently only support HTTP Code)
     * @param message Custom message
     * @return Structured response
     * @param <T> Data
     */
    public static <T> GeneralResponse<MetadataResponse, T> buildResponse(final T data, Integer code, String message) {
        // Generate Code
        // By default it is 200 OK if caller not define the code
        if (null == code) {
            code = 200;
        }

        // Generate message based on HTTP Status message if caller not specify message
        if (null == message) {
            message = HttpStatusCode.valueOf(code).toString();
        }

        // Create metadata instance
        MetadataResponse metadata = new MetadataResponse(message, LocalDateTime.now());

        // Generate general response instance
        GeneralResponse<MetadataResponse, T> response = new GeneralResponse<>();
        response.setMetadata(metadata);
        response.setData(data);

        return response;
    }

    /**
     * Generate Response using custom message and USE HTTPSTATUSCODE class
     * @param data Data to return
     * @param code Status code using HttpStatusCode class
     * @param message Custom Messgge
     * @return Structured response
     * @param <T>
     */
    public static <T> GeneralResponse<MetadataResponse, T> buildResponse(final T data, HttpStatusCode code, String message){
        return buildResponse(data, code.value(), message);
    }
}
