package org.example.hw10.client;

import org.example.hw10.dto.ProductInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class ProductClient {
    private final WebClient webClient;

    public ProductClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<ProductInfo> getProductInfo(Long id) {
        return webClient
                .get()
                .uri("http://localhost:8080/prods/{id}", id)
                .retrieve()
                .onStatus(
                        status -> status.value() == 404,
                        response -> Mono.empty()
                )
                .bodyToMono(ProductInfo.class);
    }
}
