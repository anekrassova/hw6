package org.example.hw6.controller;

import org.example.hw6.dto.DeliveryPost;
import org.example.hw6.model.Delivery;
import org.example.hw6.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {
    private final DeliveryService deliveryService;

    @Autowired
    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping()
    public Delivery createDelivery (@RequestBody DeliveryPost deliveryPost) {
        return deliveryService.createDelivery(deliveryPost.getProductId(), deliveryPost.getAddress());
    }
}
