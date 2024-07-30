package com.arduino.shop_api.repository;

import com.arduino.shop_api.entity.Category;
import com.arduino.shop_api.entity.Product;
import org.springframework.data.domain.Limit;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAll();

    List<Product> findByCategory(Category category);

    @Query(value = "select avg(rate) as avg FROM product_ratings p where p.product_id = ?1 limit 1", nativeQuery = true)
    Float getProductRating(Long productId);
}
