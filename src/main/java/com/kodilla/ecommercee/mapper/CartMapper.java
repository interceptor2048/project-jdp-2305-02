package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.service.OrderService;
import com.kodilla.ecommercee.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartMapper {

    @Autowired
    OrderService orderDbService;

    @Autowired
    UserService userDbService;


    public CartDto mapToCartDto(final Cart cart) {
        return new CartDto(
                cart.getId(),
                getIdFromUser(cart),
                getIdFromOrder(cart)
        );
    }


    private Long getIdFromUser(Cart cart) {
        try {
            return cart.getUser().getId();
        } catch (Exception e) {
            return null;
        }
    }

    private Long getIdFromOrder(Cart cart) {
        try {
            return cart.getOrder().getOrderId();
        } catch (Exception e) {
            return null;
        }
    }
}