package com.example.thuva.api.service;

import com.example.thuva.api.entity.CartEntity;
import com.example.thuva.api.model.Item;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService{

    @Override
    public List<Item> addCartItemByCustomerId(String customerId, Item item) {
        return List.of();
    }

    @Override
    public List<Item> addOrReplaceItemByCustomerId(String customerId, Item item) {
        return List.of();
    }

    @Override
    public void deleteCart(String customerId) {

    }

    @Override
    public void deleteItemFromCart(String customerId, String itemId) {

    }

    @Override
    public CartEntity getCartByCustomerId(String customerId) {
        return null;
    }

    @Override
    public List<Item> getCartItemsByCustomerId(String customerId) {
        return List.of();
    }

    @Override
    public Item getCartItemByItemId(String customerId, String itemId) {
        return null;
    }
}
