package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity(name = "carts")
public class Cart {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(mappedBy = "cart")
    @JoinColumn(name = "userId")
    private User user;

    @OneToOne(mappedBy = "cart")
    private Order order;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "CART_PRODUCTS")
    private List<Product> cartProducts;
}
