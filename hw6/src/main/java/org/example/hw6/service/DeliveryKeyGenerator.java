package org.example.hw6.service;

import org.example.hw6.dto.DeliveryPost;
import org.springframework.stereotype.Component;

@Component
public class DeliveryKeyGenerator {
    public String generate(DeliveryPost request) {
        return "delivery" +
                request.getProductId() + ":" +
                request.getAddress().toLowerCase().trim();
    }
}
