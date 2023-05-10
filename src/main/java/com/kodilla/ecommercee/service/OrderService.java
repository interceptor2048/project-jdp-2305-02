package com.kodilla.ecommercee.service;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.domain.OrderStatus;
import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.exception.OrderNotFoundException;
import com.kodilla.ecommercee.mapper.OrderMapper;
import com.kodilla.ecommercee.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public void createOrder(OrderDto orderDto) {
        orderDto.setOrderStatus(OrderStatus.CREATED);
        orderRepository.save(orderMapper.mapToOrder(orderDto));
    }

    public Order getOrder(Long orderId) {
        return orderRepository.findByOrderId(orderId).orElseThrow(() -> new OrderNotFoundException("Order with id " + orderId + " not Found"));
    }

    public Order updateOrder(Long orderId, OrderDto orderDto) {
        Order orderToUpdate = getOrder(orderId);
        orderToUpdate.setUser(null);
        orderToUpdate.setOrderStatus(orderDto.getOrderStatus());
        orderRepository.save(orderToUpdate);
        return orderToUpdate;
    }

    public void deleteOrder(Long orderId) {
        checkIfOrderExists(orderId);
        orderRepository.deleteByOrderId(orderId);
    }

    private void checkIfOrderExists(Long orderId) {
        if (!orderRepository.existsById(orderId)) {
            throw new OrderNotFoundException("Order with id " + orderId + " not Found");
        }
    }
}
