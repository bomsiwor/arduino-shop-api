package com.arduino.shop_api.controller;

import com.arduino.shop_api.model.response.GeneralResponse;
import com.arduino.shop_api.model.response.MetadataResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.arduino.shop_api.model.response.ResponseGenerator.buildResponse;

@RestController
@RequestMapping(value="${base.path}")
public class GeneralController {

    @GetMapping(value = "")
    public ResponseEntity<GeneralResponse<MetadataResponse, String>> index() {
        GeneralResponse<MetadataResponse, String> response = buildResponse(null, 200, "Hello, Coffee!");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
