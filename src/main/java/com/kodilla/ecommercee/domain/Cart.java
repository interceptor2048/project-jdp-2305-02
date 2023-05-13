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

    @OneToOne(mappedBy = "cart", cascade = CascadeType.PERSIST)
    @JoinColumn(name = "userId")
    private User user;

    @OneToOne(mappedBy = "cart", cascade = CascadeType.ALL)
    private Order order;

    @OneToMany(
            cascade = CascadeType.PERSIST,
            targetEntity =  CartProducts.class,
            fetch = FetchType.LAZY,
            mappedBy = "cart")
    private List<CartProducts> cartProducts;
}
