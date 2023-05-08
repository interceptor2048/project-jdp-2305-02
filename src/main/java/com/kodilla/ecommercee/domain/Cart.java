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
    @Column(name = "cart_id", unique = true)
    private Long id;

    @OneToOne(mappedBy = "cart")
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "cart")
    private Order order;

    @ManyToMany(mappedBy = "cartList")
    private List<Product> listOfProducts  = new ArrayList<>();
}
