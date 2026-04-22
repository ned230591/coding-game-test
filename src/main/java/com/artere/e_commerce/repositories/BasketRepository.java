package com.artere.e_commerce.repositories;


import com.artere.e_commerce.entities.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository extends JpaRepository<Basket, Long> {
}
