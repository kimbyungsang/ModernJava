package com.example.thuva.api.entity;

import com.example.thuva.api.model.Order;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "order")
@Getter
@Setter
public class OrderEntity {

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "TOTAL")
    private BigDecimal total;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private Order.StatusEnum status;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    private UserEntity userEntity;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ID", insertable = true, updatable = false)
    private AddressEntity addressEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PAYMENT_ID", referencedColumnName = "ID")
    private PaymentEntity paymentEntity;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SHIPMENT_ID", referencedColumnName = "ID")
    private ShipmentEntity shipmentEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CARD_ID", referencedColumnName = "ID")
    private CardEntity cardEntity;

    @Column(name = "ORDER_DATE")
    private Timestamp orderDate;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "ORDER_ITEM",
    joinColumns = @JoinColumn(name = "ORDER_ID"),
    inverseJoinColumns = @JoinColumn(name = "ITEM_ID"))
    private List<ItemEntity> items = new ArrayList<>();

    @OneToOne(mappedBy = "orderEntity")
    private AuthorizationEntity authorization;

}
