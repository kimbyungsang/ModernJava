package com.example.thuva.api.repository;

import com.example.thuva.api.entity.ItemEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

public interface ItemRepository extends CrudRepository<ItemEntity, UUID> {
//    @Query(value = "select i.* from thuva.cart c, thuva.item i, thuva.\"user\" u, thuva.cart_item ci " +
//            "where u.id = :customerId and " +
//            "c.user_id=u_id and " +
//            "c.id = ci.cart_id and " +
//            "i.id = ci.item_id",
//            nativeQuery = true)

    @Query(
            value =
                    "select i.* from thuva.cart c, thuva.item i, thuva.\"user\" u, thuva.cart_item ci where u.id = :customerId and c.user_id=u.id and c.id=ci.cart_id and i.id=ci.item_id",
            nativeQuery = true)
    Iterable<ItemEntity> findByCustomerId(String customerId);

    @Modifying
    @Query(
            value = "delete from thuva.cart_item where item_id in (:ids) and cart_id = :cartId",
            nativeQuery = true)
    void deleteCartItemJoinById(List<UUID> ids, UUID cartId);
}
