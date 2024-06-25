package com.example.thuva.api.repository;

import com.example.thuva.api.entity.CartEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

public interface CartRepository extends CrudRepository<CartEntity, UUID> {
/*    @Query("select c from CartEntity c join c.user u where u.id = :customer_id")
    Optional<CartEntity> findByCustomerId(@Param("customerId") UUID customerId);*/

    @Query("select c from CartEntity c join c.user u where u.id = :customerId")
    Optional<CartEntity> findByCustomerId(@Param("customerId") UUID customerId);
}
