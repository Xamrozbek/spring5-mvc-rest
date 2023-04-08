package com.example.spring5mvcrest.mapper;

import com.example.spring5mvcrest.api.v1.model.CategoryDTO;
import com.example.spring5mvcrest.domain.Category;
import org.junit.jupiter.api.Test;
import org.mapstruct.Mapper;

import java.lang.ref.PhantomReference;

import static org.junit.jupiter.api.Assertions.*;

@Mapper
class CategoryMapperTest {

    private static final String NAME = "Joe";
    private static final Long ID = 1L;

    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Test
    void categoryToCategoryDto() {

        Category category = new Category();
        category.setId(ID);
        category.setName(NAME);

        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDto(category);

        assertEquals(categoryDTO.getId(), category.getId());

    }

    @Test
    void categoryDtoToCategory() {

        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(ID);
        categoryDTO.setName(NAME);

        Category category = categoryMapper.categoryDtoToCategory(categoryDTO);

        assertEquals(category.getId(), categoryDTO.getId());

    }
}