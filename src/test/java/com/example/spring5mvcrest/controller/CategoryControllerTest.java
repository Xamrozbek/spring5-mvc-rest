package com.example.spring5mvcrest.controller;

import com.example.spring5mvcrest.api.v1.model.CategoryDTO;
import com.example.spring5mvcrest.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.any;


import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest extends AbstractRestControllerTest {
    private static final Long ID = 1L;
    private static final String NAME = "Joe";
    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
//        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @Test
    void getAllCategories() {
    }

    @Test
    void createCategory() throws Exception {
        CategoryDTO category = new CategoryDTO();
        category.setId(ID);
        category.setName(NAME);

        CategoryDTO returnCategory = new CategoryDTO();
        returnCategory.setId(category.getId());
        returnCategory.setName(category.getName());

        when(categoryService.createCategory(any(CategoryDTO.class))).thenReturn(returnCategory);

        mockMvc.perform(post("/api/v1/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJson(category)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo("Joe")));
    }

    @Test
    void updatedCategory() throws Exception {
        CategoryDTO category = new CategoryDTO();
        category.setId(ID);
        category.setName(NAME);

        CategoryDTO returnCategory = new CategoryDTO();
        returnCategory.setId(category.getId());
        returnCategory.setName(category.getName());

        when(categoryService.updateCategory(anyLong(), any(CategoryDTO.class))).thenReturn(returnCategory);

        mockMvc.perform(put("/api/v1/categories/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJson(category)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Joe")));
    }

    @Test
    void deleteCategory() throws Exception {
        mockMvc.perform(delete("/api/v1/categories/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(categoryService).deleteCategory(anyLong());
    }
}