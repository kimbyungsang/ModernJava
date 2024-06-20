package com.example.thuva.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Entity
@Table(name = "cart")
@Getter
@Setter
public class CartEntity {

    @Id
    @GeneratedValue
    @Column(name = "ID", updatable = false, nullable = false)
    private UUID uid;

    @OneToOne
    @JoinColumn(name="USER_ID", referencedColumnName = "ID")
    private  UserEntity user;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "CART_ITEM",
            joinColumns = @JoinColumn(name = "CART_ID"),
            inverseJoinColumns = @JoinColumn(name = "ITEM_ID")
    )
    private List<ItemEntity> items = new ArrayList<>();

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;

        CartEntity that = (CartEntity) obj;
        return user.equals(that.user) && Objects.equals(items, that.items);

    }

    @Override
    public int hashCode() {
        return Objects.hash(user, items);
    }
}