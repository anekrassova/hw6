package org.example.hw6.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.hw6.model.DeliveryStatus;

@Getter
@Setter
public class DeliveryUpdateStatus {
    private Long deliveryId;
    private DeliveryStatus status;
}
