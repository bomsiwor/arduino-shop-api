package com.arduino.shop_api.service.contract;

import com.arduino.shop_api.entity.Category;
import com.arduino.shop_api.model.request.CategoryRequest;
import com.arduino.shop_api.model.response.GeneralResponse;
import com.arduino.shop_api.model.response.MetadataResponse;

import java.util.List;

public interface ICategoryService {
    GeneralResponse<MetadataResponse, List<Category>> getAll();

    GeneralResponse<MetadataResponse,Category> get(int id) throws Exception;

    GeneralResponse<MetadataResponse,Boolean> create(CategoryRequest request);

    GeneralResponse<MetadataResponse,Boolean> update(CategoryRequest request, int id);

    GeneralResponse<MetadataResponse,Boolean> delete(int id);
}
