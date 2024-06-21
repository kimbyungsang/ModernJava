package com.example.thuva.api.service;

import com.example.thuva.api.entity.ItemEntity;
import com.example.thuva.api.model.Item;

import java.util.List;

public interface ItemService {

    ItemEntity toEntity(Item m);
    Item toModel(ItemEntity e);
    List<ItemEntity> toEntityList(List<Item> items);
    List<Item> toList(List<ItemEntity> items);


}
