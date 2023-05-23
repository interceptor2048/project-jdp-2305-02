package com.kodilla.ecommercee.domain;
import lombok.*;
import javax.persistence.*;
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

    @ManyToMany(
            fetch = FetchType.LAZY)
    @JoinTable(name = "ORDER_PRODUCTS")
    private List<Product> cartProducts;

    public Order(Long orderId, OrderStatus orderStatus) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
    }
}
