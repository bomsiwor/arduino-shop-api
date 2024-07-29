package com.arduino.shop_api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Getter
@Setter
@JsonPropertyOrder({"id", "name", "description", "location","specification", "price", "unit", "createdAt", "updatedAt", "category"})
public class Product extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "price")
    private BigDecimal price;

    @Column(nullable = false, name = "unit")
    private String unit;

    @Column(nullable = false, name = "location")
    private String location;

    @Column(columnDefinition = "TEXT", name="specification")
    private String specification;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id",nullable = false)
    private Category category;
}
