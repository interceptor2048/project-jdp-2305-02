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
    private Long orderId;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}
