package com.kodilla.ecommercee.dto;

import com.kodilla.ecommercee.domain.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OrderDto {
    private Long orderId;
    private Long userId;
    private Long cartId;
    private OrderStatus orderStatus;
}
