package com.example.thuva.api.repository;

import com.example.thuva.api.entity.OrderItemEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface OrderItemRepository extends CrudRepository<OrderItemEntity, UUID> {
}
