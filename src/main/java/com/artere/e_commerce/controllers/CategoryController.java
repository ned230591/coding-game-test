package com.artere.e_commerce.controllers;
import com.artere.e_commerce.entities.Category;
import com.artere.e_commerce.services.CategoryService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @PostMapping
    public Category create(@RequestBody Category category) {
        return service.create(category);
    }

    @PutMapping("/{id}")
    public Category update(@PathVariable Long id, @RequestBody Category category) {
        return service.update(id, category);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping
    public List<Category> findAll() {
        return service.findAll();
    }

   //link c sub category to parent
    @PutMapping("/{id}/parent/{parentId}")
    public Category linkParent(@PathVariable Long id, @PathVariable Long parentId) {
        Category child = service.findById(id);
        Category parent = service.findById(parentId);

        parent.addSubCategory(child);
        return service.create(parent);
    }

    //unlink category
    @PutMapping("/{id}/unlink-parent")
    public Category unlinkParent(@PathVariable Long id) {
        Category child = service.findById(id);
        child.setParent(null);
        return service.create(child);
    }
}