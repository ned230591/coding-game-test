package com.artere.e_commerce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonBackReference
    private Category parent;

    @OneToMany(mappedBy = "parent",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonManagedReference
    private List<Category> subCategory = new ArrayList<>();

    @OneToMany(mappedBy = "category",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Product> products = new ArrayList<>();

    public void addSubCategory(Category subCategory) {
        subCategory.setParent(this);
        this.subCategory.add(subCategory);
    }

    public void removeSubCategory(Category subCategory) {
        subCategory.setParent(null);
        this.subCategory.remove(subCategory);
    }

    public void addProduct(Product product) {
        product.setCategory(this);
        products.add(product);
    }

    public void removeProduct(Product product) {
        product.setCategory(null);
        products.remove(product);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", parent=" + parent +
                ", subCategory=" + subCategory +
                ", products=" + products +
                '}';
    }
}