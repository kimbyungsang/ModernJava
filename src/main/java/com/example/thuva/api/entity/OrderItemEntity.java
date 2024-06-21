package com.example.thuva.api.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;



@Entity
@Table(name = "order_item")
@Getter
public class OrderItemEntity {

    @Id
    @GeneratedValue
    @Column(name = "ID", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "order_id")
    private UUID orderId;

    @Column(name = "item_id")
    private UUID itemId;

    public OrderItemEntity setId(UUID id) {
        this.id = id;
        return this;
    }

    public OrderItemEntity setOrderId(UUID orderId) {
        this.orderId = orderId;
        return this;
    }

    public OrderItemEntity setItemId(UUID itemId) {
        this.itemId = itemId;
        return this;
    }
}
