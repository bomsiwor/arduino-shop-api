package com.arduino.shop_api.repository;

import com.arduino.shop_api.entity.Category;
import com.arduino.shop_api.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAll();

    List<Product> findByCategory(Category category);
}
