package com.artere.e_commerce.controllers;

import com.artere.e_commerce.entities.Basket;
import com.artere.e_commerce.services.BasketService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/baskets")
public class BasketController {

    private final BasketService service;

    public BasketController(BasketService service) {
        this.service = service;
    }

    @PostMapping
    public Basket createBasket() {
        return service.createBasket();
    }

    @PostMapping("/{basketId}/products/{productId}")
    public Basket addProduct(@PathVariable Long basketId,
                             @PathVariable Long productId,
                             @RequestParam int quantity) {
        return service.addProduct(basketId, productId, quantity);
    }

    @PutMapping("/items/{itemId}")
    public Basket updateItem(@PathVariable Long itemId,
                             @RequestParam int quantity) {
        return service.updateItem(itemId, quantity);
    }

    @DeleteMapping("/items/{itemId}")
    public Basket removeItem(@PathVariable Long itemId) {
        return service.removeItem(itemId);
    }

    @GetMapping("/{basketId}/total")
    public double getTotal(@PathVariable Long basketId) {
        return service.getTotal(basketId);
    }
}
