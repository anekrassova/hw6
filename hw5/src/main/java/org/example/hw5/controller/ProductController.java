package org.example.hw5.controller;

import org.example.hw5.dto.ProductRequest;
import org.example.hw5.model.Product;
import org.example.hw5.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/prods")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping()
    public Mono<Product> createProduct(@RequestBody ProductRequest productRequest) {
        return productService.createProduct(productRequest.getName(), productRequest.getPrice());
    }

    @GetMapping("/{id}")
    public Mono<Product> getProduct(@PathVariable Long id){
        return productService.getProduct(id);
    }

    @GetMapping()
    public Flux<Product> getAllProduct(){
        return productService.getAllProducts();
    }

    @PutMapping("/{id}")
    public Mono<Product> updateProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest){
        return productService.updateProduct(id, productRequest.getName(), productRequest.getPrice());
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteProduct(@PathVariable Long id){
        return productService.deleteProduct(id);
    }
}
