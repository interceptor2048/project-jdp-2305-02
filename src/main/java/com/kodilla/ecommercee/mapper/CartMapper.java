package com.kodilla.ecommercee.mapper;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.service.OrderService;
import com.kodilla.ecommercee.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartMapper {
    private final OrderService orderDbService;
    private final UserService userDbService;

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