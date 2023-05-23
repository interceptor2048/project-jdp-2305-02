package com.kodilla.ecommercee.mapper;
import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.service.OrderService;
import com.kodilla.ecommercee.service.UserService;
import com.kodilla.ecommercee.controller.CartController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartMapper {
    private final ProductMapper productMapper;
    private final OrderService orderDbService;
    private final UserService userDbService;

    public CartDto mapToCartDto(final Cart cart) {
        return new CartDto(
                cart.getUser().getId(),
                cart.getId(),
                cart.getOrder().getOrderId(),
                productMapper.mapToProductDtoList(cart.getCartProducts())
        );
    }

    public Cart mapToCart(CartDto cartDto) {
        return new Cart(
                cartDto.getCartId(),
                userDbService.getUser(cartDto.getUserId()),
                orderDbService.getOrder(cartDto.getOrderId()),
                productMapper.mapToProductList(cartDto.getProductDtoList()));
    }

    public List<ProductDto> mapToCartDtoProducts(Cart cart) {
        return productMapper.mapToProductDtoList(cart.getCartProducts());
    }

}
