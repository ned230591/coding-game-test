package com.artere.e_commerce.services;

import com.artere.e_commerce.entities.Basket;
import com.artere.e_commerce.entities.BasketItem;
import com.artere.e_commerce.entities.Product;
import com.artere.e_commerce.repositories.BasketItemRepository;
import com.artere.e_commerce.repositories.BasketRepository;
import com.artere.e_commerce.repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class BasketService {

    private final BasketRepository basketRepo;
    private final ProductRepository productRepo;
    private final BasketItemRepository itemRepo;

    public BasketService(BasketRepository basketRepo,
                         ProductRepository productRepo,
                         BasketItemRepository itemRepo) {
        this.basketRepo = basketRepo;
        this.productRepo = productRepo;
        this.itemRepo = itemRepo;
    }

    public Basket createBasket() {
        return basketRepo.save(new Basket());
    }

    public Basket addProduct(Long basketId, Long productId, int quantity) {
        Basket basket = basketRepo.findById(basketId).orElseThrow();
        Product product = productRepo.findById(productId).orElseThrow();

        BasketItem item = new BasketItem();
        item.setProduct(product);
        item.setQuantity(quantity);

        basket.addItem(item);

        return basketRepo.save(basket);
    }

    public Basket updateItem(Long itemId, int quantity) {
        BasketItem item = itemRepo.findById(itemId).orElseThrow();
        item.setQuantity(quantity);
        itemRepo.save(item);

        return item.getBasket();
    }

    public Basket removeItem(Long itemId) {
        BasketItem item = itemRepo.findById(itemId).orElseThrow();
        Basket basket = item.getBasket();

        basket.removeItem(item);
        itemRepo.delete(item);

        return basket;
    }

    public double getTotal(Long basketId) {
        Basket basket = basketRepo.findById(basketId).orElseThrow();
        return basket.getTotal();
    }
}