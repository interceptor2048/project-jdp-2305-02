package com.kodilla.ecommercee.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long productId;
    private String name;
    private String description;
    private BigDecimal price;
    private Long groupId;
}
