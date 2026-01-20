package org.example.hw5.controller;

import org.example.hw5.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/product/api")
public class ClientController {
    private final ProductService productService;

    @Autowired
    public ClientController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/exists/{id}")
    public Mono<Boolean> productExistById(@PathVariable Long id) {
        return productService.existsProductById(id);
    }
}
