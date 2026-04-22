package com.artere.e_commerce.services;

import com.artere.e_commerce.entities.Category;
import com.artere.e_commerce.entities.Product;
import com.artere.e_commerce.repositories.CategoryRepository;
import com.artere.e_commerce.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class ProductService {

    private final ProductRepository productRepo;
    private final CategoryRepository categoryRepo;

    public ProductService(ProductRepository productRepo, CategoryRepository categoryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }

    public Product create(Product product, Long categoryId) {
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        product.setCategory(category);
        return productRepo.save(product);
    }

    public Product update(Long id, Product updated) {
        Product existing = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        existing.setName(updated.getName());
        existing.setPrice(updated.getPrice());
        existing.setStockQuantity(updated.getStockQuantity());

        return productRepo.save(existing);
    }

    public void delete(Long id) {
        productRepo.deleteById(id);
    }

    public Product moveProduct(@PathVariable Long productId,
                               @PathVariable Long categoryId) {

        Product product = productRepo.findById(productId)
                .orElseThrow();

        Category category = categoryRepo.findById(categoryId)
                .orElseThrow();

        product.setCategory(category);
        return productRepo.save(product);
    }
}