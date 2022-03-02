package com.crud.basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.basic.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
}
