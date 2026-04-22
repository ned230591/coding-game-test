package com.artere.e_commerce.repositories;

import com.artere.e_commerce.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
