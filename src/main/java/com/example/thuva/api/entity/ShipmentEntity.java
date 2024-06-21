package com.example.thuva.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CollectionId;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "shipment")
@Getter
@Setter
public class ShipmentEntity {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private UUID id;

    @Column(name = "EST_DELIVER_DATE")
    private Timestamp estDeliveryDate;

    @Column(name = "CARRIER")
    private String carrier;
}
