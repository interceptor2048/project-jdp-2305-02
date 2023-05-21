package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.*;
import com.kodilla.ecommercee.exception.ProductNotFoundException;
import com.kodilla.ecommercee.exception.UserNotFoundException;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.ProductRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

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

    public Cart getUserCart(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        return user.getCart();
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
        Order order = new Order();
        order.setUser(user);
        order.setCart(user.getCart());
        order.setOrderStatus(OrderStatus.CREATED);
        orderRepository.save(order);
        createEmptyCart(userId);
    }
}