package com.example.thuva.api.controller;

import com.example.thuva.api.CartApi;
import com.example.thuva.api.hateoas.CartRepresentationModelAssembler;
import com.example.thuva.api.model.Cart;
import com.example.thuva.api.model.Item;
import com.example.thuva.api.service.CartService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import static org.springframework.http.ResponseEntity.accepted;
import static org.springframework.http.ResponseEntity.ok;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class CartController implements CartApi {

    private static final Logger log = LoggerFactory.getLogger(CartController.class);
    private final CartService service;
    private final CartRepresentationModelAssembler assembler;

    public CartController(CartService service, CartRepresentationModelAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @Override
    public ResponseEntity<List<Item>> addCartItemsByCustomerId(String customerId, Item item) throws Exception {
        log.info("Request for customer ID: {}\n Item: {}", customerId, item);
        return ok(service.addCartItemByCustomerId(customerId, item));
    }

    @Override
    public ResponseEntity<List<Item>> addOrReplaceItemsByCustomerId(String customerId, Item item) throws Exception {
        log.info("Request for customer ID: {}\n Item: {}", customerId, item);
        return ok(service.addOrReplaceItemByCustomerId(customerId, item));
    }

    @Override
    public ResponseEntity<Void> deleteCart(String customerId) throws Exception {
        service.deleteCart(customerId);
        return accepted().build();
    }

    @Override
    public ResponseEntity<Void> deleteItemFromCart(String customerId, String itemId) throws Exception {
        service.deleteItemFromCart(customerId, itemId);
        return  accepted().build();
    }

    @Override
    public ResponseEntity<Cart> getCartByCustomerId(String customerId) throws Exception {
            return ok(assembler.toModel(service.getCartByCustomerId(customerId)));
    }

    @Override
    public ResponseEntity<List<Item>> getCartItemsByCustomerId(String customerId) throws Exception {
        return ok(service.getCartItemsByCustomerId(customerId));
    }

    @Override
    public ResponseEntity<Item> getCartItemsByItemId(String customerId, String itemId) throws Exception {
        return ok(service.getCartItemByItemId(customerId, itemId));
    }
}
