package com.example.spring5mvcrest.service.impl;

import com.example.spring5mvcrest.api.v1.model.CategoryDTO;
import com.example.spring5mvcrest.mapper.CategoryMapper;
import com.example.spring5mvcrest.repositories.CategoryRepository;
import com.example.spring5mvcrest.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;


    @Override
    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::categoryToCategoryDto)
                .toList();
    }

    @Override
    public CategoryDTO getCategpryByName(String name) {
        return categoryRepository.findByName(name)
                .map(categoryMapper::categoryToCategoryDto)
                .orElse(null);
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        var category = categoryRepository.save(categoryMapper.categoryDtoToCategory(categoryDTO));
        return categoryMapper.categoryToCategoryDto(category);
    }

    @Override
    public CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO) {
        var category = categoryMapper.categoryDtoToCategory(categoryDTO);
        var updatecsategory = categoryRepository.save(category);
        updatecsategory.setId(id);
        return categoryMapper.categoryToCategoryDto(updatecsategory);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }


}
