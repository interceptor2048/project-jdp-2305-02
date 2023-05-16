package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.mapper.ProductMapper;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    private final OrderService orderService;

    private final ProductMapper productMapper;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public Cart getCartById(Long cartId) {
        return cartRepository.findById(cartId).orElse(new Cart());
    }

    public Cart save(final Cart cart) {
        return cartRepository.save(cart);
    }

    public void createEmptyCart(Long userId) {
        Cart emptyCart = new Cart();
        Cart cart = cartRepository.save(emptyCart);

        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        cart.setUser(user);
        user.setCart(cart);
        userRepository.save(user);
    }

    public List<Product> getUserCart(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return user.getCart().getCartProducts();
    }

    public void addItemToCart(Long userId, Long productId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        Cart cartToAddItem = user.getCart();
        Product productToAdd = productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
        cartToAddItem.getCartProducts().add(productToAdd);
        cartRepository.save(cartToAddItem);
    }

    public void deleteItemFromCart(Long userId, Long productId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        user.getCart().getCartProducts().removeIf(product -> product.getId().equals(productId));
        userRepository.save(user);
    }

    public void createOrder(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        OrderDto orderDto = new OrderDto(1L,userId,user.getCart().getId(),OrderStatus.CREATED);
        orderService.createOrder(orderDto);
    }
}