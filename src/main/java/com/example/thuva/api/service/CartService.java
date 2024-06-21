package com.example.thuva.api.service;

import com.example.thuva.api.entity.CartEntity;
import com.example.thuva.api.model.Item;
import jakarta.validation.Valid;

import java.util.List;

public interface CartService {
    public List<Item> addCartItemByCustomerId(String customerId, @Valid Item item);
    public List<Item> addOrReplaceItemByCustomerId(String customerId, @Valid Item item);
    public void deleteCart(String customerId);
    public void deleteItemFromCart(String customerId, String itemId);
    public CartEntity getCartByCustomerId(String customerId);
    public List<Item> getCartItemsByCustomerId(String customerId);
    public Item getCartItemByItemId(String customerId, String itemId);
}
