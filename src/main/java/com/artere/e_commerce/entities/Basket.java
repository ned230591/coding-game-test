package com.artere.e_commerce.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
@Entity
@Table(name = "baskets")
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "basket", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BasketItem> items = new ArrayList<>();

    public void addItem(BasketItem item) {
        item.setBasket(this);
        items.add(item);
    }

    public void removeItem(BasketItem item) {
        item.setBasket(null);
        items.remove(item);
    }

    public double getTotal() {
        return items.stream()
                .mapToDouble(i -> i.getProduct().getPrice().doubleValue() * i.getQuantity())
                .sum();
    }

}