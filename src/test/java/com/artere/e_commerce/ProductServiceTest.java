package com.artere.e_commerce;



import com.artere.e_commerce.entities.Category;
import com.artere.e_commerce.entities.Product;
import com.artere.e_commerce.repositories.CategoryRepository;
import com.artere.e_commerce.repositories.ProductRepository;
import com.artere.e_commerce.services.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void shouldCreateProductWithCategory() {
        Category category = new Category();
        category.setId(1L);

        Product product = new Product();
        product.setName("PRODUCT 11");
        product.setPrice(BigDecimal.valueOf(900));

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(productRepository.save(any())).thenAnswer(i -> i.getArguments()[0]);

        Product result = productService.create(product, 1L);

        assertThat(result.getCategory()).isEqualTo(category);
        verify(productRepository).save(product);
    }
}