package com.arduino.shop_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "${base.path}")
public class ProductController {

    @GetMapping(value = "/products")
    public String getAll() {
        return "All products";
    }
}
