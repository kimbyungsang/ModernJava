package com.example.thuva.api.repository;

import com.example.thuva.api.entity.TagEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TagRepository extends CrudRepository<TagEntity, UUID> {
}
