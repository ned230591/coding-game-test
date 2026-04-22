package com.artere.e_commerce;

import com.artere.e_commerce.entities.Basket;
import com.artere.e_commerce.entities.BasketItem;
import com.artere.e_commerce.entities.Product;
import com.artere.e_commerce.repositories.BasketRepository;

import com.artere.e_commerce.services.BasketService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class BasketServiceTest {

    @Mock
    BasketRepository basketRepo;


    @InjectMocks
    BasketService service;


    @Test
    void shouldCalculateTotal() {
        Basket basket = new Basket();

        Product product = new Product();
        product.setPrice(BigDecimal.valueOf(10));

        BasketItem item = new BasketItem();
        item.setProduct(product);
        item.setQuantity(3);

        basket.addItem(item);

        when(basketRepo.findById(1L)).thenReturn(Optional.of(basket));

        double total = service.getTotal(1L);

        assertThat(total).isEqualTo(30);
    }
}