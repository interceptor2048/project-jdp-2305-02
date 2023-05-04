package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.dto.OrderDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/orders")
public class OrderController {

    List<OrderDto> orderDtoList = new ArrayList<OrderDto>() {
        {
            add(new OrderDto(1L, 1L, 1L));
        }
    };

    @GetMapping
    public ResponseEntity<List<OrderDto>> getOrders() {
        return ResponseEntity.ok(orderDtoList);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> addOrder(@RequestBody OrderDto orderDto) {
        orderDtoList.add(orderDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable Long orderId) {
        for (OrderDto order : orderDtoList) {
            if (order.getOrderId().equals(orderId)) {
                return ResponseEntity.ok(order);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping(path = "/{orderId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderDto> updateOrder(@PathVariable Long orderId, @RequestBody OrderDto orderDto) {
        for (OrderDto order : orderDtoList) {
            if (order.getOrderId().equals(orderId)) {
                orderDtoList.remove(order);
                orderDtoList.add(orderDto);
                return ResponseEntity.ok(orderDto);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        for (OrderDto order : orderDtoList) {
            if (order.getOrderId().equals(orderId)) {
                orderDtoList.remove(order);
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.notFound().build();
    }


}
