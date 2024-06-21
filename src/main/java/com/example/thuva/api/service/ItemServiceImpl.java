package com.example.thuva.api.service;

import com.example.thuva.api.entity.ItemEntity;
import com.example.thuva.api.model.Item;

import java.util.List;

public class ItemServiceImpl implements ItemService{

    @Override
    public ItemEntity toEntity(Item m) {
        return null;
    }

    @Override
    public Item toModel(ItemEntity e) {
        return null;
    }

    @Override
    public List<ItemEntity> toEntityList(List<Item> items) {
        return List.of();
    }

    @Override
    public List<Item> toList(List<ItemEntity> items) {
        return List.of();
    }
}
