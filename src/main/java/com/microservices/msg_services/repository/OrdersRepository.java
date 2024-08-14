package com.microservices.msg_services.repository;

import com.microservices.msg_services.entity.Orders;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrdersRepository extends MongoRepository<Orders, UUID> {
}

