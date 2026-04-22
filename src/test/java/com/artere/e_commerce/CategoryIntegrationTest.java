package com.artere.e_commerce;

import com.artere.e_commerce.entities.Category;
import com.artere.e_commerce.entities.Product;
import com.artere.e_commerce.repositories.CategoryRepository;
import com.artere.e_commerce.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class CategoryIntegrationTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    @Transactional
    void shouldPersistCategoryAndProductRelationship() {
        Category category = new Category();
        category.setName("CAT1");

        category = categoryRepository.save(category);

        Product product = new Product();
        product.setName("PROD1");
        product.setPrice(BigDecimal.valueOf(10));

        product.setCategory(category);


        productRepository.save(product);

        Category found = categoryRepository.findById(category.getId()).orElseThrow();

        assertThat(found.getProducts()).hasSize(1);
    }



    @Test
    void shouldLinkProductToCategory() {
        Category category = new Category();
        category.setName("CAT1");
        category = categoryRepository.save(category);

        Product product = new Product();
        product.setName("PROD1");
        product.setPrice(BigDecimal.valueOf(100));
        product = productRepository.save(product);
        product.setCategory(category);
        productRepository.save(product);
        Category found = categoryRepository
                .findById(category.getId()).orElseThrow();

        assertThat(found.getProducts()).hasSize(1);
        assertThat(found.getProducts().get(0).getName()).isEqualTo("PROD1");
    }

@Test
void shouldUnlinkProductFromCategory() {
    Category category = new Category();
    category.setName("CAT1");
    category = categoryRepository.save(category);

    Product product = new Product();
    product.setName("PROD1");
    product.setPrice(BigDecimal.valueOf(20));
    product.setCategory(category);
    product = productRepository.save(product);
    category.getProducts().remove(product);
    product.setCategory(null);
    productRepository.save(product);
    Category found = categoryRepository.findById(category.getId()).orElseThrow();
    assertThat(found.getProducts()).isEmpty();
}




}