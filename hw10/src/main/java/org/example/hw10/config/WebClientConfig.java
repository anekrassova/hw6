package org.example.hw10.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Bean
    public WebClient webClient(@Value("${product.service.username}") String username,
                               @Value("${product.service.password}") String password) {
        return WebClient.builder()
                .defaultHeaders(headers ->
                        headers.setBasicAuth(username, password)
                )
                .filter(logResponse())
                .build();
    }

    private static ExchangeFilterFunction logResponse() {
        return ExchangeFilterFunction.ofResponseProcessor(response ->
                response.bodyToMono(String.class)
                        .doOnNext(body -> {
                            System.out.println("---- WebClient RESPONSE ----");
                            System.out.println("Status: " + response.statusCode());
                            System.out.println("Body: " + body);
                            System.out.println("----------------------------");
                        })
                        .map(body -> response.mutate().body(body).build())
        );
    }
}
