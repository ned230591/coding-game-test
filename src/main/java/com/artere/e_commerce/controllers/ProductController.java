package com.artere.e_commerce.controllers;
import com.artere.e_commerce.entities.Product;
import com.artere.e_commerce.services.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping("/category/{categoryId}")
    public Product create(@RequestBody Product product,
                          @PathVariable Long categoryId) {
        return service.create(product, categoryId);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id,
                          @RequestBody Product product) {
        return service.update(id, product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PutMapping("/{productId}/move/{categoryId}")
    public Product moveProduct(@PathVariable Long productId,
                               @PathVariable Long categoryId) {

       return service.moveProduct(productId , categoryId) ;
    }
}
