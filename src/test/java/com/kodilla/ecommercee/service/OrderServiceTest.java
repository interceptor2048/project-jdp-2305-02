package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Cart;
import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.OrderStatus;
import com.kodilla.ecommercee.domain.User;
import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.repository.OrderRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class OrderServiceTest {

    private OrderService orderService;
    @Mock
    private OrderRepository orderRepository;
    @InjectMocks
    private OrderMapper orderMapper;

    private Order order;
    private Long id;
    private List<Order> list;

    @BeforeEach
    void setUp() {
        list = new ArrayList<>();
        order = new Order(1L, OrderStatus.DONE);
        list.add(order);
        orderService = new OrderService(orderRepository, orderMapper);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getOrders() {
        //Given
        when(orderRepository.findAll()).thenReturn(list);

        //When
        List<Order> resultList = orderService.getOrders();

        //Then
        assertThat(resultList.size()).isEqualTo(1);
    }

    @Test
    void createOrder() {
        //Given
        User user = new User(1L, "asd", 1, 1);
        Cart cart = new Cart(user, null);
        Order order = new Order(OrderStatus.CREATED, cart, user);
        OrderDto orderDto = orderMapper.mapToOrderDto(order);


        //When
        orderService.createOrder(orderDto);
        ArgumentCaptor<Order> orderArgumentCaptor = ArgumentCaptor.forClass(Order.class);
        verify(orderRepository).save(orderArgumentCaptor.capture());
        Order capturedOrder = orderArgumentCaptor.getValue();

        //Then
        assertThat(capturedOrder.getOrderId()).isEqualTo(orderDto.getOrderId());
        assertThat(capturedOrder.getOrderStatus()).isEqualTo(OrderStatus.CREATED);
    }

    @Test
    void getOrder() {
        //Given

        //When
        when(orderRepository.findByOrderId(id)).thenReturn(Optional.ofNullable(order));
        Order resultOrder = orderService.getOrder(id);

        //Then
        assertThat(resultOrder.getOrderStatus()).isEqualTo(order.getOrderStatus());
        assertThat(resultOrder.getOrderId()).isEqualTo(order.getOrderId());
    }

    @Test
    void updateOrder() {
        //Given
        OrderDto orderDto = new OrderDto(1L, 1L, 1L, OrderStatus.DONE);
        ArgumentCaptor<Order> orderArgumentCaptor = ArgumentCaptor.forClass(Order.class);
        when(orderRepository.findByOrderId(id)).thenReturn(Optional.ofNullable(order));

        //When
        orderService.updateOrder(id, orderDto);
        verify(orderRepository).save(orderArgumentCaptor.capture());
        Order resultOrder = orderArgumentCaptor.getValue();

        //Then
        assertThat(resultOrder.getOrderStatus()).isEqualTo(orderDto.getOrderStatus());

    }

    @Test
    void deleteOrder() {
        //Given
        when(orderRepository.existsById(id)).thenReturn(true);
        //When
        orderService.deleteOrder(id);

        //Then
        verify(orderRepository).deleteByOrderId(id);
    }

}