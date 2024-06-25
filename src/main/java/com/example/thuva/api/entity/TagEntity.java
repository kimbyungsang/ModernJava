package com.example.thuva.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "tag")
@Getter
public class TagEntity {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private UUID id;

    @Column(name = "NAME")
    private String name;

    public TagEntity setId(UUID id) {
        this.id = id;
        return this;
    }

    public TagEntity setName(String name) {
        this.name = name;
        return this;
    }
}
