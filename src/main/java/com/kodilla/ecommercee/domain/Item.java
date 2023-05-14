package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table (name = "ITEMS")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "ITEM_ID")
    private Long itemId;

    public Item(List<Product> product, Cart cart) {
        this.cart = cart;
        this.product = product;
    }

    @ManyToMany (
            targetEntity = Product.class,
            mappedBy = "items",
            fetch = FetchType.LAZY
    )
    private List<Product> product;


    @ManyToOne
    @JoinColumn(name = "CART_ID")
    private Cart cart;
}
