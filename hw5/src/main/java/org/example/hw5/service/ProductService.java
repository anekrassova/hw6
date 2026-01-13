package org.example.hw5.service;

import org.example.hw5.model.Product;
import org.example.hw5.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepo productRepo;

    @Autowired
    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    //create
    public Product createProduct(String productName, Double price) {
        if (productName == null || productName.isBlank()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
        if (price == null || price <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }

        Product product = new Product(productName, price);
        return productRepo.save(product);
    }

    //read one
    public Product getProduct(Long id) {
        return productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    //read all
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    //update
    public Product updateProduct(Long id, String productName, Double price) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(productName);
        product.setPrice(price);

        return productRepo.save(product);
    }

    //delete
    public void deleteProduct(Long id) {
        if (!productRepo.existsById(id)) {
            throw new RuntimeException("Product not found");
        }
        productRepo.deleteById(id);
    }

    public boolean existsProductById(Long id) {
        return productRepo.existsById(id);
    }
}
