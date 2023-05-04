package com.kodilla.ecommercee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ProductDto {

    private Long productId;
    private String name;
    private String description;
    private BigDecimal price;
    private Long groupId;

}
