package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cartProducts")
public class CartProducts {

    @Id
    @GeneratedValue
    @Column(name = "cartProductsId", unique = true, nullable = false)
    private Long cartProductsId;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "cartId")
    private Cart cart;

    @Column(name = "quantity")
    private int quantity;
    @ManyToOne
    private Order order;

}
