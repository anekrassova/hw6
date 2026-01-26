package org.example.hw6.service;

import org.example.hw6.client.ProductClient;
import org.example.hw6.model.Delivery;
import org.example.hw6.model.DeliveryStatus;
import org.example.hw6.repo.DeliveryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryService {
    private final DeliveryRepo deliveryRepo;
    private final ProductClient productClient;

    @Autowired
    public DeliveryService(DeliveryRepo deliveryRepo, ProductClient productClient) {
        this.deliveryRepo = deliveryRepo;
        this.productClient = productClient;
    }

    public Delivery createDelivery(Long productId, String address) {
        if (!productClient.productExists(productId)) {
            throw new RuntimeException("Product does not exist");
        }

        Delivery delivery = new Delivery(productId, address);
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
