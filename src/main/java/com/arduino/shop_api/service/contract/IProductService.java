package com.arduino.shop_api.service.contract;

import com.arduino.shop_api.entity.Product;
import com.arduino.shop_api.model.request.ProductRatingRequest;
import com.arduino.shop_api.model.request.ProductRequest;
import com.arduino.shop_api.model.response.GeneralResponse;
import com.arduino.shop_api.model.response.MetadataResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProductService {

    GeneralResponse<MetadataResponse, List<Product>> get(Integer categoryId);

    GeneralResponse<MetadataResponse, Product> get(int id);

    GeneralResponse<MetadataResponse, Boolean> store(ProductRequest request);

    GeneralResponse<MetadataResponse, Boolean> update(ProductRequest request, int id);

    GeneralResponse<MetadataResponse, Boolean> delete(int id);

    GeneralResponse<MetadataResponse, Float> getRating(int id);

    GeneralResponse<MetadataResponse, Boolean> postRating(int id, ProductRatingRequest request);
}
