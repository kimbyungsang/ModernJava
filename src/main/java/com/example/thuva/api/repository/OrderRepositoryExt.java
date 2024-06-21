package com.example.thuva.api.repository;

import com.example.thuva.api.entity.OrderEntity;
import com.example.thuva.api.model.NewOrder;

import java.util.Optional;

public interface OrderRepositoryExt {
    Optional<OrderEntity> insert(NewOrder m);
}
