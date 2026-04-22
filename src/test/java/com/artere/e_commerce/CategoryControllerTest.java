package com.artere.e_commerce;


import com.artere.e_commerce.controllers.CategoryController;
import com.artere.e_commerce.entities.Category;
import com.artere.e_commerce.services.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateCategory() throws Exception {
        Category category = new Category();
        category.setId(1L);
        category.setName("CAT1");

        when(service.create(any())).thenReturn(category);

        mockMvc.perform(post("/categories")
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(category)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("CAT1"));
    }

    @Test
    void shouldReturnAllCategories() throws Exception {
        Category cat = new Category();
        cat.setId(1L);
        cat.setName("name1");

        when(service.findAll()).thenReturn(List.of(cat));

        mockMvc.perform(get("/categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("name1"));
    }
}