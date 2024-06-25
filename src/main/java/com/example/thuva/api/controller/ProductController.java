package com.example.thuva.api.controller;

import com.example.thuva.api.ProductApi;
import com.example.thuva.api.hateoas.ProductRepresentationModelAssembler;
import com.example.thuva.api.model.Product;
import com.example.thuva.api.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class ProductController implements ProductApi {

    private final ProductService service;
    private final ProductRepresentationModelAssembler assembler;

    public ProductController(ProductService service, ProductRepresentationModelAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @Override
    public ResponseEntity<Product> getProduct(String id) throws Exception {
        return service.getProduct(id)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<List<Product>> queryProducts(String tag, String name, Integer page, Integer size) throws Exception {
        return ok(assembler.toListModel(service.getProducts()));
    }
}
