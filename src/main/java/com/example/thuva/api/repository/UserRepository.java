package com.example.thuva.api.repository;

import com.example.thuva.api.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

public interface UserRepository extends CrudRepository<UserEntity, UUID> {
}
