package com.example.thuva.api.repository;

import com.example.thuva.api.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {
}
