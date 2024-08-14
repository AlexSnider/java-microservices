package com.microservices.msg_services.controller;

import com.microservices.msg_services.entity.Orders;
import com.microservices.msg_services.rabbitMQ.producer.Producer;
import com.microservices.msg_services.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrdersController {
    private final OrderService orderService;
    private final Producer producer;

    public OrdersController(OrderService orderService, Producer producer) {
        this.orderService = orderService;
        this.producer = producer;
    }

    @PostMapping
    public ResponseEntity<Orders> createOrder(@RequestBody Orders order) {
        Orders createdOrder = orderService.create(order);
        producer.send("Order created: " + createdOrder.getId());
        return ResponseEntity.ok(createdOrder);
    }

    @GetMapping
    public ResponseEntity<List<Orders>> getOrders() {
        List<Orders> ordersList = orderService.list();
        producer.send("Order list retrieved");
        return ResponseEntity.ok(ordersList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable String id) {
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid UUID format for ID: " + id);
        }

        Optional<Orders> order = orderService.getById(uuid);
        if (order.isPresent()) {
            return ResponseEntity.ok(order.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Order with ID " + id + " not found.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable String id, @RequestBody Orders order) {
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid UUID format for ID: " + id);
        }

        Optional<Orders> existingOrder = orderService.getById(uuid);
        if (existingOrder.isPresent()) {
            order.setId(uuid);
            Orders updatedOrder = orderService.update(order);
            producer.send("Order updated: " + updatedOrder.getId());
            return ResponseEntity.ok(updatedOrder);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Order with ID " + id + " not found.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable String id) {
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid UUID format for ID: " + id);
        }

        Optional<Orders> existingOrder = orderService.getById(uuid);
        if (existingOrder.isPresent()) {
            orderService.delete(uuid);
            producer.send("Order deleted: " + id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Order with ID " + id + " not found.");
        }
    }
}
