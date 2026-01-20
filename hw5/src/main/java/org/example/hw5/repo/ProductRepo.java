package org.example.hw5.repo;

import org.example.hw5.model.Product;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends R2dbcRepository<Product, Long> {}
