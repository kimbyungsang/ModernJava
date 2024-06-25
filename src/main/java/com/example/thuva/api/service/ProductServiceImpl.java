package com.example.thuva.api.service;

import com.example.thuva.api.entity.ProductEntity;
import com.example.thuva.api.repository.ProductRepository;
import com.example.thuva.api.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository, TagRepository tagRepository) {
        this.repository = repository;
    }

    @Override
    public Optional<ProductEntity> getProduct(String id) {
        return repository.findById(UUID.fromString(id));
    }

    @Override
    public Iterable<ProductEntity> getProducts() {
        return repository.findAll();
    }
}
