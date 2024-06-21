package com.example.thuva.api.repository;

import com.example.thuva.api.entity.OrderEntity;
import com.example.thuva.api.model.Order;
import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends CrudRepository<OrderEntity, UUID>, OrderRepositoryExt {
    @Query("select o from OrderEntity o join o.userEntity u where u.id = :customerId")
    Iterable<OrderEntity> findByCustomerId(@Param("customerId") UUID customerId);

//    @Query("select o from OrderEntity o join o.userEntity u where u.id = :customerId")
//    Iterable<OrderEntity> findByCustomerId(@Param("customerId") UUID customerId);
}
