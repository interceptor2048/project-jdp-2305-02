package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    public List<Cart> getAllCarts() {
        return Optional.ofNullable(cartRepository.findAll()).orElse(new ArrayList<>()).stream()
                .filter(cart -> cart.getOrder() == null)
                .collect(Collectors.toList());
    }

    public Cart getCartById(Long cartId) {
        return cartRepository.findById(cartId).orElse(new Cart());
    }

    public Cart save(final Cart cart) {
        return cartRepository.save(cart);
    }

    public void deleteCartById(final Long cartId) {
        cartRepository.deleteById(cartId);
    }

}