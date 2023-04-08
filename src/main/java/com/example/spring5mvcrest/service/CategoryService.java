package com.example.spring5mvcrest.service;

import com.example.spring5mvcrest.api.v1.model.CategoryDTO;


import java.util.List;


public interface CategoryService {

    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategpryByName(String name);

    CategoryDTO createCategory(CategoryDTO categoryDTO);

    CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO);

    void deleteCategory(Long id);
}
