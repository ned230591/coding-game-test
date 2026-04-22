package com.artere.e_commerce.repositories;


import com.artere.e_commerce.entities.BasketItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketItemRepository extends JpaRepository<BasketItem, Long> {
}