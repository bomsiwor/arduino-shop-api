package com.arduino.shop_api.controller;

import com.arduino.shop_api.entity.Product;
import com.arduino.shop_api.model.request.ProductRatingRequest;
import com.arduino.shop_api.model.request.ProductRequest;
import com.arduino.shop_api.model.response.GeneralResponse;
import com.arduino.shop_api.model.response.MetadataResponse;
import com.arduino.shop_api.service.contract.IProductService;
import com.arduino.shop_api.service.impl.ProductService;
import com.arduino.shop_api.util.ValidationUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "${base.path}")
public class ProductController {
    private final IProductService service;

    public ProductController(ProductService productService) {
        this.service = productService;
    }

    @GetMapping(value = "/products")
    public ResponseEntity<GeneralResponse<MetadataResponse, List<Product>>> getAll(@RequestParam(name = "categoryId", required = false) Integer categoryId) {
        return new ResponseEntity<>(this.service.get(categoryId), HttpStatus.OK);
    }

    @GetMapping(value = "/product/{id}")
    public ResponseEntity<GeneralResponse<MetadataResponse, Product>> getProduct(@PathVariable int id) {
        return new ResponseEntity<>(this.service.get(id), HttpStatus.OK);
    }

    @PostMapping(value = "/product")
    public ResponseEntity<GeneralResponse<MetadataResponse, Boolean>> addProduct(@RequestBody ProductRequest request) {
        // Validating request
        ValidationUtil validator = new ValidationUtil();
        validator.required(request.getName(), "Product Name");
        validator.min(request.getName(), 3, "Product Name");
        validator.required(request.getLocation(), "Product manufacturer location");
        validator.required(request.getPrice(), "Product price");
        validator.min(request.getPrice().intValueExact(),1, "Product price");
        validator.required(request.getUnit(), "Product unit");
        validator.required(request.getCategoryId(), "Product category");

        return new ResponseEntity<>(this.service.store(request), HttpStatus.CREATED);
    }

    @PutMapping(value="/product/{id}")
    public ResponseEntity<GeneralResponse<MetadataResponse, Boolean>> updateProduct(@PathVariable int id, @RequestBody ProductRequest request) {
        // Validating request
        ValidationUtil validator = new ValidationUtil();
        validator.required(request.getName(), "Product Name");
        validator.min(request.getName(), 3, "Product Name");
        validator.required(request.getLocation(), "Product manufacturer location");
        validator.required(request.getPrice(), "Product price");
        validator.min(request.getPrice().intValueExact(),1, "Product price");
        validator.required(request.getUnit(), "Product unit");
        validator.required(request.getCategoryId(), "Product category");

        return new ResponseEntity<>(this.service.update(request,id), HttpStatus.OK);
    }

    @DeleteMapping(value="/product/{id}")
    public ResponseEntity<GeneralResponse<MetadataResponse, Boolean>> deleteProduct(@PathVariable int id) {
        return new ResponseEntity<>(this.service.delete(id), HttpStatus.OK);
    }

    @GetMapping(value="/product/rating/{id}")
    public ResponseEntity<GeneralResponse<MetadataResponse, Float>> getRating(@PathVariable int id) {
        return new ResponseEntity<>(this.service.getRating(id), HttpStatus.OK);
    }

    @PostMapping(value="/product/rating/{id}")
    public ResponseEntity<GeneralResponse<MetadataResponse, Boolean>> postRating(@PathVariable int id, @RequestBody ProductRatingRequest request) {
        // Validating Request
        ValidationUtil validator = new ValidationUtil();
        validator.required(request.getRating(), "Rating");
        validator.min(request.getRating(), 0,"Rating");
        validator.max(request.getRating(), 5, "Rating");
        validator.validate();

        return new ResponseEntity<>(this.service.postRating(id, request), HttpStatus.OK);
    }
}
