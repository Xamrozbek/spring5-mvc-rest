package com.example.spring5mvcrest.service.impl;

import com.example.spring5mvcrest.domain.Category;
import com.example.spring5mvcrest.mapper.CategoryMapper;
import com.example.spring5mvcrest.repositories.CategoryRepository;
import com.example.spring5mvcrest.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    private CategoryService categoryService;

    private static final Long ID = 1L;
    private static final String NAME = "Joe";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryServiceImpl(categoryRepository, CategoryMapper.INSTANCE);

    }


    @Test
    void getAllCategories() {

        List<Category> categoryList = List.of(new Category(), new Category(), new Category());

        when(categoryRepository.findAll()).thenReturn(categoryList);

        var result = categoryService.getAllCategories();

        assertEquals(3, result.size());

    }

    @Test
    void getByName() {
        Category category = new Category();
        category.setId(ID);
        category.setName(NAME);

        when(categoryRepository.findByName(anyString())).thenReturn(Optional.of(category));

        var dto = categoryService.getCategpryByName(NAME);

        assertEquals(1L, dto.getId());
        assertEquals(NAME, dto.getName());
    }
}
