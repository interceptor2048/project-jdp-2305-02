package com.kodilla.ecommercee.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDto {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Long groupId;

}