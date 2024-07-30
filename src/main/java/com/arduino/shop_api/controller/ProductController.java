package com.arduino.shop_api.controller;

import com.arduino.shop_api.entity.Product;
import com.arduino.shop_api.model.request.ProductRatingRequest;
import com.arduino.shop_api.model.request.ProductRequest;
import com.arduino.shop_api.model.response.GeneralResponse;
import com.arduino.shop_api.model.response.MetadataResponse;
import com.arduino.shop_api.service.contract.IProductService;
import com.arduino.shop_api.service.impl.ProductService;
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
        return new ResponseEntity<>(this.service.store(request), HttpStatus.CREATED);
    }

    @PutMapping(value="/product/{id}")
    public ResponseEntity<GeneralResponse<MetadataResponse, Boolean>> updateProduct(@PathVariable int id, @RequestBody ProductRequest request) {
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
        return new ResponseEntity<>(this.service.postRating(id, request), HttpStatus.OK);
    }
}
