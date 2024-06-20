package com.example.thuva.api.controller;

import com.example.thuva.api.CartApi;
import com.example.thuva.api.model.Cart;
import com.example.thuva.api.model.Item;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import static org.springframework.http.ResponseEntity.ok;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class CartController implements CartApi {

    private static final Logger log = LoggerFactory.getLogger(CartController.class);

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return CartApi.super.getRequest();
    }

    @Override
    public ResponseEntity<List<Item>> addCartItemsByCustomerId(String customerId, @Valid Item item) throws Exception {
        log.info("Request for customer ID: {}\nItem: {}",customerId, item);
        return ok(Collections.EMPTY_LIST);

//    return CartApi.super.addCartItemsByCustomerId(customerId, item);
    }

    @Override
    public ResponseEntity<List<Item>> addOrReplaceItemsByCustomerId(String customerId, Item item) throws Exception {
        return CartApi.super.addOrReplaceItemsByCustomerId(customerId, item);
    }

    @Override
    public ResponseEntity<Void> deleteCart(String customerId) throws Exception {
        return CartApi.super.deleteCart(customerId);
    }

    @Override
    public ResponseEntity<Void> deleteItemFromCart(String customerId, String itemId) throws Exception {
        return CartApi.super.deleteItemFromCart(customerId, itemId);
    }

    @Override
    public ResponseEntity<List<Cart>> getCartByCustomerId(String customerId) {
        // return CartApi.super.getCartByCustomerId(customerId);
        throw new RuntimeException("Manual Exception thrown");
    }

    @Override
    public ResponseEntity<List<Item>> getCartItemsByCustomerId(String customerId) {
        return null;
    }

    @Override
    public ResponseEntity<List<Item>> getCartItemsByItemId(String customerId, String itemId) throws Exception {
        return CartApi.super.getCartItemsByItemId(customerId, itemId);
    }
}
