package com.example.thuva.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "payment")
@Getter
@Setter
public class PaymentEntity {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private UUID id;

    @Column(name = "AUTHORIZED")
    private boolean authorized;

    @Column(name = "MESSAGE")
    private String message;

    @OneToOne(mappedBy = "paymentEntity")
    private OrderEntity orderEntity;


}
