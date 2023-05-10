package com.kodilla.ecommercee.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue
    @Column
    private Long orderId;
    @Enumerated(EnumType.STRING)
    @Column
    private OrderStatus orderStatus;
    @OneToOne
    private Cart cart;
    @ManyToOne
    private User user;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "order")
    private List<CartProducts> cartProducts;

    public Order (Long orderId, OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Order(OrderStatus orderStatus, Cart cart, User user) {
        this.orderStatus = orderStatus;
        this.cart = cart;
        this.user = user;
    }
}
