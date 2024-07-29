package com.arduino.shop_api.service.impl;

import com.arduino.shop_api.entity.Category;
import com.arduino.shop_api.exception.NotFoundException;
import com.arduino.shop_api.model.request.CategoryRequest;
import com.arduino.shop_api.model.response.GeneralResponse;
import com.arduino.shop_api.model.response.MetadataResponse;
import com.arduino.shop_api.repository.CategoryRepository;
import com.arduino.shop_api.service.contract.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import static com.arduino.shop_api.model.response.ResponseGenerator.buildResponse;

@Service
public class CategoryService implements ICategoryService {
    @Autowired private CategoryRepository repository;

    @Override
    public GeneralResponse<MetadataResponse, List<Category>> getAll() {
        // Retrive from database
        List<Category> categories = repository.findAll();

        // Create response instance using response builder
        return buildResponse(categories, 200, null);
    }

    @Override
    public GeneralResponse<MetadataResponse, Category> get(int id) throws Exception {
        // Get data by ID
        Optional<Category> result = this.repository.findById((long) id);

        // Throw error if data not exists
        if (!result.isPresent()) {
            throw new NotFoundException("Data not found!");
        }

        // Build response instance if data exists
        return buildResponse(result.get(), 200, null);
    }

    @Override
    public GeneralResponse<MetadataResponse, Boolean> create(CategoryRequest request) {
        // Use persist fnction to create new category
        Boolean result = persist(request, null);

        // Return true if successfully save
        return buildResponse(result, HttpStatus.CREATED, null);
    }

    @Override
    public GeneralResponse<MetadataResponse, Boolean> update(CategoryRequest request, int id) {
        // Search By ID
        Optional<Category> category = this.repository.findById((long) id);
        if (!category.isPresent()) {
            throw new NotFoundException("Category data not found!");
        }

        // Use persist fnction to create new category
        Boolean result = persist(request, category.get());

        // Return true if successfully save
        return buildResponse(result, HttpStatus.OK, null);
    }

    @Override
    public GeneralResponse<MetadataResponse, Boolean> delete(int id) {
        // Find data by ID
        Optional<Category> category = this.repository.findById((long) id);

        if (!category.isPresent()) {
            throw new NotFoundException("Category data not found!");
        }

        this.repository.delete(category.get());

        return buildResponse(true, HttpStatus.OK, "Category data deleted");
    }

    private Boolean persist(CategoryRequest request, Category category) {
        // Map request data to category instance
        // Create new category instance if category parameters is null
        if (null == category) {
            category = new Category();
        }

        category.setName(request.getName());
        category.setDescription(request.getDescription());
        category.setUpdatedAt(OffsetDateTime.now());

        // Store in memory and flush to database
        this.repository.saveAndFlush(category);

        return true;
    }
}
