package com.artere.e_commerce.entities;



import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal price;
    private int stockQuantity;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}