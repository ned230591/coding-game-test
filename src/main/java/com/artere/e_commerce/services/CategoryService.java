package com.artere.e_commerce.services;

import com.artere.e_commerce.entities.Category;
import com.artere.e_commerce.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository repo;

    public CategoryService(CategoryRepository repo) {
        this.repo = repo;
    }

    public Category create(Category category) {
        return repo.save(category);
    }

    public Category update(Long id, Category updated) {
        Category existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        existing.setName(updated.getName());
        existing.setDescription(updated.getDescription());
        return repo.save(existing);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public List<Category> findAll() {
        return repo.findAll();
    }

    public Category findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }
}