package org.example.hw6.service;

import jakarta.transaction.Transactional;
import org.example.hw6.client.ProductClient;
import org.example.hw6.exception.DuplicateDeliveryException;
import org.example.hw6.model.Delivery;
import org.example.hw6.model.DeliveryStatus;
import org.example.hw6.repo.DeliveryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.example.hw6.dto.DeliveryPost;

import java.time.Duration;
import java.util.List;

@Service
public class DeliveryService {
    private final DeliveryRepo deliveryRepo;
    private final ProductClient productClient;
    private final RedisTemplate<String, String> redisTemplate;
    private final DeliveryKeyGenerator deliveryKeyGenerator;

    private static final Duration DUPLICATE_TTL = Duration.ofSeconds(2);

    @Autowired
    public DeliveryService(DeliveryRepo deliveryRepo, ProductClient productClient, RedisTemplate<String, String> redisTemplate, DeliveryKeyGenerator deliveryKeyGenerator) {
        this.deliveryRepo = deliveryRepo;
        this.productClient = productClient;
        this.redisTemplate = redisTemplate;
        this.deliveryKeyGenerator = deliveryKeyGenerator;
    }

    @Transactional
    public Delivery createDelivery(DeliveryPost deliveryPost) {
        String key = deliveryKeyGenerator.generate(deliveryPost);

        boolean isNew = redisTemplate
                .opsForValue()
                .setIfAbsent(key, "LOCKED", DUPLICATE_TTL);

        if (Boolean.FALSE.equals(isNew)) {
            throw new RuntimeException("Duplicate delivery request");
        }

        if (!productClient.productExists(deliveryPost.getProductId())) {
            throw new DuplicateDeliveryException("Product does not exist");
        }

        Delivery delivery = new Delivery(deliveryPost.getProductId(), deliveryPost.getAddress());
        return deliveryRepo.save(delivery);
    }

    public Delivery updateDeliveryStatus(Long deliveryId, DeliveryStatus deliveryStatus) {
        Delivery delivery = deliveryRepo.findById(deliveryId)
                .orElseThrow(() -> new RuntimeException("Delivery not found"));
        delivery.setStatus(deliveryStatus);

        return deliveryRepo.save(delivery);
    }

    public Delivery findDeliveryById(Long id) {
        List<Delivery> deliveries = deliveryRepo.findByProductIdOrderByCreatedAtDesc(id);
        return deliveries.isEmpty() ? null : deliveries.get(0);
    }
}
