package com.arduino.shop_api.controller;

import com.arduino.shop_api.entity.Category;
import com.arduino.shop_api.model.request.CategoryRequest;
import com.arduino.shop_api.model.response.GeneralResponse;
import com.arduino.shop_api.model.response.MetadataResponse;
import com.arduino.shop_api.service.impl.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "${base.path}")
public class CategoryController {

    private static final Logger LOG = LoggerFactory.getLogger(CategoryController.class);

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping(value = "/categories")
    public ResponseEntity<GeneralResponse<MetadataResponse, List<Category>>> getAll() {
        return new ResponseEntity<>(this.service.getAll(),HttpStatus.OK);
    }

    @GetMapping(value="/category/{id}")
    public ResponseEntity<GeneralResponse<MetadataResponse, Category>> detail(@PathVariable int id) throws Exception {
        return new ResponseEntity<>(this.service.get(id), HttpStatus.CREATED);
    }

    @PostMapping(value = "/category")
    public ResponseEntity<GeneralResponse<MetadataResponse, Boolean>> store(@RequestBody CategoryRequest request) {
        return new ResponseEntity<>(this.service.create(request), HttpStatus.CREATED);
    }

    @PutMapping(value="/category/{id}")
    public ResponseEntity<GeneralResponse<MetadataResponse, Boolean>> update(@PathVariable Integer id, @RequestBody CategoryRequest request) {
        return new ResponseEntity<>(this.service.update(request, id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/category/{id}")
    public ResponseEntity<GeneralResponse<MetadataResponse, Boolean>> delete(@PathVariable int id) {
        return new ResponseEntity<>(this.service.delete(id), HttpStatus.OK);
    }
}
