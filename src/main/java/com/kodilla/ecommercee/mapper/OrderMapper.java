package com.kodilla.ecommercee.mapper;

import com.kodilla.ecommercee.domain.Order;
import com.kodilla.ecommercee.dto.OrderDto;
import com.kodilla.ecommercee.repository.CartRepository;
import com.kodilla.ecommercee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderMapper {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;


    public Order mapToOrder(final OrderDto orderDto) {
        return new Order(
                orderDto.getOrderId(),
                orderDto.getOrderStatus(),
                null,
                null,
               null
        );
    }

    public OrderDto mapToOrderDto(final Order order) {
        return new OrderDto(
                order.getOrderId(),
                order.getUser().getId(),
                order.getCart().getId(),
                order.getOrderStatus()
        );
    }

    public List<Order> mapToOrderList(final List<OrderDto> orderList) {
        return orderList.stream().map(this::mapToOrder).collect(Collectors.toList());
    }

    public List<OrderDto> mapToDtoOrderList(final List<Order> orderList) {
        return orderList.stream().map(this::mapToOrderDto).collect(Collectors.toList());
    }
}
