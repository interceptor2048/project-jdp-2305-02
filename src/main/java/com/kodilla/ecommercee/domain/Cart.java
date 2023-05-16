package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity(name = "carts")
public class Cart {

    @Id
    @GeneratedValue
    @Column(name = "cartId", unique = true)
    private Long id;

    @OneToOne(cascade = CascadeType.PERSIST, mappedBy = "cart")
    @JoinColumn(name = "userId")
    private User user;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "cart")
    private Order order;
    @OneToMany(
            targetEntity =  CartProducts.class,
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST,
            mappedBy = "cart")
    private List<CartProducts> cartProducts;

    public Cart(User user, List<CartProducts> cartProducts) {
        this.user = user;
        this.cartProducts = cartProducts;
    }
}

