package com.example.thuva.api.service;

import com.example.thuva.api.entity.ProductEntity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.Optional;

public interface ProductService {

    Optional<ProductEntity> getProduct(@Min(value = 1L, message = "Invalid product ID.") String id);
    @NotNull Iterable<ProductEntity> getProducts();
}
