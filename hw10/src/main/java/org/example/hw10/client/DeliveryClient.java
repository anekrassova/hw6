package org.example.hw10.client;

import org.example.hw10.dto.DeliveryInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class DeliveryClient {
    private final WebClient webClient;

    public DeliveryClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<DeliveryInfo> getDeliveryInfo(Long id) {
        return webClient
                .get()
                .uri("http://localhost:8081/delivery/info/{id}", id)
                .retrieve()
                .onStatus(
                        status -> status.value() == 404,
                        response -> Mono.empty()
                )
                .bodyToMono(DeliveryInfo.class);
    }
}
