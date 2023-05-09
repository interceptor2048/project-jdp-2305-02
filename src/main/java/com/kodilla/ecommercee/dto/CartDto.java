package com.kodilla.ecommercee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CartDto {

    private Long Id;
    private String name;
    private String description;
    private BigDecimal price;
    private Long groupId;
}