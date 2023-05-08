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
    //Here we do not need to change the names because the orders are not specified in project.
    //We'll have to keep those names in mind when pairing classes later on, though.
}
