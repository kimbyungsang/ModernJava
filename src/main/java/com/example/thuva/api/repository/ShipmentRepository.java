package com.example.thuva.api.repository;

import com.example.thuva.api.entity.ShipmentEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * @author : github.com/sharmasourabh
 * @project : Chapter08 - Modern API Development with Spring and Spring Boot Ed 2
 */
public interface ShipmentRepository extends CrudRepository<ShipmentEntity, UUID> {}
