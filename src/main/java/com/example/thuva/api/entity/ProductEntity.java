package com.example.thuva.api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "product")
@Getter
public class ProductEntity {

    @Id
    @GeneratedValue
    @Column(name = "ID", updatable = false, nullable = false)
    private UUID id;

    @NotNull(message = "Product name is required.!")
    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "COUNT")
    private int count;

    @Column(name = "IMAGE_URL")
    private String imageURL;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "PRODUCT_TAG",
            joinColumns = @JoinColumn(name = "PRODUCT_ID"),
            inverseJoinColumns = @JoinColumn(name = "TAG_ID")
    )
    private List<TagEntity> tags = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<ItemEntity> items;

    public ProductEntity(UUID id,
                         @NotNull(message = "Product name is required") String name,
                         String description,
                         BigDecimal price,
                         int count,
                         String imageURL) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.count = count;
        this.imageURL = imageURL;
    }

    public ProductEntity() {
    }

    public ProductEntity setId(UUID id) {
        this.id = id;
        return this;
    }

    public ProductEntity setName(@NotNull(message = "Product name is required.!") String name) {
        this.name = name;
        return this;
    }

    public ProductEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public ProductEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public ProductEntity setCount(int count) {
        this.count = count;
        return this;
    }

    public ProductEntity setImageURL(String imageURL) {
        this.imageURL = imageURL;
        return this;
    }

    public ProductEntity setTags(List<TagEntity> tags) {
        this.tags = tags;
        return this;
    }

    public ProductEntity setItems(List<ItemEntity> items) {
        this.items = items;
        return this;
    }
}
