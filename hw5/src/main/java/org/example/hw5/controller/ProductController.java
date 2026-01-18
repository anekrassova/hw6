package org.example.hw5.controller;

import org.example.hw5.dto.ProductRequest;
import org.example.hw5.model.Product;
import org.example.hw5.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/prods")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping()
    public Product createProduct(@RequestBody ProductRequest productRequest) {
        return productService.createProduct(productRequest.getName(), productRequest.getPrice());
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id){
        return productService.getProduct(id);
    }

    @GetMapping()
    public CompletableFuture<List<Product>> getAllProduct(){
        return productService.getAllProducts();
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody ProductRequest productRequest){
        return productService.updateProduct(id, productRequest.getName(), productRequest.getPrice());
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }
}
