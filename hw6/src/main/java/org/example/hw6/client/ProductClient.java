package org.example.hw6.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "hw5",
        url = "http://localhost:8080"
)
public interface ProductClient {
    @GetMapping("/product/api/exists/{id}")
    boolean productExists(@PathVariable("id") Long id);
}
