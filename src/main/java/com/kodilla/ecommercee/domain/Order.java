package com.kodilla.ecommercee.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
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
}
