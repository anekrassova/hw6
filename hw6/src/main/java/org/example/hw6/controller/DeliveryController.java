package org.example.hw6.controller;

import org.example.hw6.dto.DeliveryPost;
import org.example.hw6.dto.DeliveryUpdateStatus;
import org.example.hw6.model.Delivery;
import org.example.hw6.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {
    private final DeliveryService deliveryService;

    @Autowired
    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @GetMapping("/info/{id}")
    public Delivery getProductDeliveryInfo(@PathVariable Long id){
        return deliveryService.findDeliveryById(id);
    }

    @PostMapping()
    public Delivery createDelivery (@RequestBody DeliveryPost deliveryPost) {
        return deliveryService.createDelivery(deliveryPost.getProductId(), deliveryPost.getAddress());
    }

    @PutMapping
    public Delivery updateDeliveryStatus (@RequestBody DeliveryUpdateStatus deliveryUpdateStatus) {
        return deliveryService.updateDeliveryStatus(deliveryUpdateStatus.getDeliveryId(), deliveryUpdateStatus.getStatus());
    }
}
