package com.kodilla.ecommercee.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartDto {
    private Long userId;
    private Long cartId;
    private Long orderId;

}