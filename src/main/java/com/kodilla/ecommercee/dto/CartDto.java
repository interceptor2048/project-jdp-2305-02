package com.kodilla.ecommercee.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartDto {

    private Long userId;
    private Long cartId;
    private Long orderId;
    private List<ProductDto> productDtoList;
}