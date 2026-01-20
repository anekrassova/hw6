package org.example.hw5.service;

import org.example.hw5.model.Product;
import org.example.hw5.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepo productRepo;

    @Autowired
    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    //create
    public Mono<Product> createProduct(String productName, Double price) {
        if (productName == null || productName.isBlank()) {
            return Mono.error(new IllegalArgumentException("Product name cannot be blank"));
        }
        if (price == null || price <= 0) {
            return Mono.error(new IllegalArgumentException("Product price cannot be less than 0"));
        }

        Product product = new Product(productName, price);
        return productRepo.save(product);
    }

    //read one
    public Mono<Product> getProduct(Long id) {
        return productRepo.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Product not found")));
    }

    //read all
    public Flux<Product> getAllProducts() {
        return productRepo.findAll();
    }

    //update
    public Mono<Product> updateProduct(Long id, String productName, Double price) {
        if (productName == null || productName.isBlank()) {
            return Mono.error(new IllegalArgumentException("Product name cannot be blank"));
        }

        if (price == null || price <= 0) {
            return Mono.error(new IllegalArgumentException("Product price cannot be less than 0"));
        }

        return productRepo.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Product not found")))
                .flatMap(product -> {
                    product.setName(productName);
                    product.setPrice(price);
                    return productRepo.save(product);
                });
    }

    //delete
    public Mono<Void> deleteProduct(Long id) {
        return productRepo.existsById(id)
                .flatMap(exists -> {
                    if (!exists) {
                        return Mono.error(new RuntimeException("Product not found"));
                    } else {
                        return productRepo.deleteById(id);
                    }
                });
    }

    public Mono<Boolean> existsProductById(Long id) {
        return productRepo.existsById(id);
    }
}
