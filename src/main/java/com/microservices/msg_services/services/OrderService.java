package com.microservices.msg_services.services;

import com.microservices.msg_services.entity.Orders;
import com.microservices.msg_services.repository.OrdersRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {
    private final OrdersRepository ordersRepository;


    public OrderService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }


    public Orders create(Orders order) {
        order.setId(UUID.randomUUID());
        order.setOrder_date(new Date());
        return ordersRepository.save(order);
    }


    public List<Orders> list() {
        return ordersRepository.findAll();
    }


    public Optional<Orders> getById(UUID id) {
        return ordersRepository.findById(id);
    }

    public Orders update(Orders order) {
        order.setOrder_date(new Date());
        return ordersRepository.save(order);
    }


    public void delete(UUID id) {
        ordersRepository.deleteById(id);
    }
}
