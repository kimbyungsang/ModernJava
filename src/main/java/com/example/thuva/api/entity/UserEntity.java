package com.example.thuva.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "\"user\"")
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private UUID id;

    @NotNull(message = "Username is required")
    @Basic(optional = false)
    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "FIRT_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "USER_STATUS")
    private String userStatus;

    /**
     * 한명의 유저는 하나 이상의 주소를 가진다. 주소 테이블에 유저를 FK 하며 될것 같다.
     */
    @OneToMany
    @JoinTable(name = "USER_ADDRESS",
    joinColumns = @JoinColumn(name = "USER_ID"),
    inverseJoinColumns = @JoinColumn(name = "ADDRESS_ID"))
    private List<AddressEntity> addressEntity = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<CardEntity> cards;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, orphanRemoval = true)
    private CartEntity cart;

    @OneToMany(mappedBy = "userEntity", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<OrderEntity> orders;
}
