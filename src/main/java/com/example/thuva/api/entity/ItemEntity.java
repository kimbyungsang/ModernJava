package com.example.thuva.api.entity;

import com.example.thuva.api.model.Cart;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "item")
@Getter
public class ItemEntity {

    @Id
    @GeneratedValue
    @Column(name = "ID", updatable = false, nullable = false)
    private UUID id;


    @Column(name = "UNIT_PRICE")
    private BigDecimal price;

    @Column(name = "QUANTITY")
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID")
    private ProductEntity product;

    @ManyToMany(mappedBy = "items", fetch = FetchType.LAZY)
    private List<CartEntity> cart;

    @ManyToMany(mappedBy = "items", fetch = FetchType.LAZY)
    private List<OrderEntity> orders;

    public ItemEntity setId(UUID id) {
        this.id = id;
        return this;
    }

    public ItemEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public ItemEntity setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public ItemEntity setProduct(ProductEntity product) {
        this.product = product;
        return this;
    }

    public ItemEntity setCart(List<CartEntity> cart) {
        this.cart = cart;
        return this;
    }

    public ItemEntity setOrders(List<OrderEntity> orders) {
        this.orders = orders;
        return this;
    }
}
