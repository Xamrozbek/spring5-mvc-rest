package com.example.spring5mvcrest.repositories;

import com.example.spring5mvcrest.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;


public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String name);
}
