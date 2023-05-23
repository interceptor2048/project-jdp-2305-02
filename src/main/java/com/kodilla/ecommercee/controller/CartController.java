package com.kodilla.ecommercee.controller;
import com.kodilla.ecommercee.dto.CartDto;
import com.kodilla.ecommercee.dto.ProductDto;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.mapper.CartMapper;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/cart")
@CrossOrigin("*")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartDbService;
    private final CartMapper cartMapper;
    private final CartMapper productMapper;


    @PostMapping("{userId}")
    public void createEmptyCart(@PathVariable("userId") Long userId) {
        cartDbService.createEmptyCart(userId);
    }

    @GetMapping("{userId}")
    public List<ProductDto> getCart(@PathVariable("userId") Long userId) throws UserNotFoundException {
        return productMapper.mapToCartDtoProducts(cartDbService.getUserCart(userId));
    }

    @PutMapping("{userId}/{productId}")
    public void addItemToCart(@PathVariable("userId") Long userId, @PathVariable Long productId) throws UserNotFoundException {
        cartDbService.addItemToCart(userId, productId);
    }

    @DeleteMapping("{userId}/{productId}")
    public void deleteItemFromCart(@PathVariable("userId") Long userId, @PathVariable("productId") Long productId) throws UserNotFoundException {
        cartDbService.deleteItemFromCart(userId, productId);
    }
  
    @PostMapping("/createOrder/{userId}")
    public void createOrder(@PathVariable Long userId) throws UserNotFoundException {
        cartDbService.createOrder(userId);
    }
}
