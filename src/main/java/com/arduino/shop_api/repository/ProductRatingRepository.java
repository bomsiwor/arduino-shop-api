package com.arduino.shop_api.repository;

import com.arduino.shop_api.entity.ProductRating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRatingRepository extends JpaRepository<ProductRating, Long> {

}
