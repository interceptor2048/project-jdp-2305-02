package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
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

    @OneToOne(mappedBy = "cart")
    @JoinColumn(name = "userId")
    private User user;

    @OneToOne(mappedBy = "cart")
    private Order order;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "cartId")
    private List<CartProducts> cartProducts;
}
