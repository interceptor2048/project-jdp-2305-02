package com.kodilla.ecommercee.repository;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.OrderStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class OrderRepositoryTestSuite {
    @Autowired
    private OrderRepository orderRepository;
    private Order order;
    private Long id;

    @BeforeEach
    void setUp() {
        order = new Order(null, OrderStatus.PENDING);
        orderRepository.save(order);
        id = order.getOrderId();
    }

    @AfterEach
    void tearDown() {
        try {
            orderRepository.deleteByOrderId(id);
        } catch (Exception e) {
        }
    }

    @Test
    void save() {
        //Given
        Order newOrder = new Order(5L, OrderStatus.PENDING);

        //When
        orderRepository.save(newOrder);

        //Then
        assertThat(orderRepository.findAll().size()).isEqualTo(2);
    }

    @Test
    void findAll() {
        //Given

        //When

        //Then
        assertThat(orderRepository.findAll().size()).isEqualTo(1);
    }


    @Test
    void findById() {
        //Given

        //When
        Optional<Order> orderOptional = orderRepository.findByOrderId(id);

        //Then
        assertTrue(orderOptional.isPresent());
        assertEquals(orderOptional.get().getOrderId(), order.getOrderId());
    }

    @Test
    void findByNotExistingId() {
        //Given

        //When
        Optional<Order> orderOptional = orderRepository.findByOrderId(100L);

        //Then
        assertThat(orderOptional).isEmpty();
    }

    @Test
    void deleteById() {
        //Given

        //When
        orderRepository.deleteByOrderId(id);

        //Then
        assertFalse(orderRepository.existsById(id));
    }
}