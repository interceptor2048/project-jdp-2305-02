package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue
    @Column
    private Long orderId;
    @Enumerated(EnumType.STRING)
    @Column
    private OrderStatus orderStatus;

    @OneToOne()
    private Cart cart;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "order")
    private List<CartProducts> cartProducts;

    public Order (Long orderId, OrderStatus orderStatus) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
    }
}
