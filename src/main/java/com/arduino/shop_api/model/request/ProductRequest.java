package com.arduino.shop_api.model.request;

import com.arduino.shop_api.model.BaseRequest;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductRequest extends BaseRequest {
    private String location;

    private BigDecimal price;

    private String specification;

    private String unit;

    private Long categoryId;
}
