package com.artere.e_commerce;


import com.artere.e_commerce.entities.Category;
import com.artere.e_commerce.repositories.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void shouldSaveAndRetrieveCategory() {
        Category category = new Category();
        category.setName("TEST CATEGORY");
        category.setDescription("TEST");

        Category saved = categoryRepository.save(category);

        assertThat(saved.getId()).isNotNull();

        Category found = categoryRepository.findById(saved.getId()).orElse(null);
        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo("TEST CATEGORY");
    }
}