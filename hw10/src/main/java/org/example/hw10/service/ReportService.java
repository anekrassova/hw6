package org.example.hw10.service;

import org.example.hw10.client.DeliveryClient;
import org.example.hw10.client.ProductClient;
import org.example.hw10.dto.DeliveryInfo;
import org.example.hw10.dto.ProductInfo;
import org.example.hw10.dto.ReportItem;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ReportService {
    private final DeliveryClient deliveryClient;
    private final ProductClient productClient;

    public ReportService(DeliveryClient deliveryClient, ProductClient productClient) {
        this.deliveryClient = deliveryClient;
        this.productClient = productClient;
    }

    public Flux<ReportItem> aggregateDeliveryInfo(List<Long> productIds) {
        System.out.println("-ReportService- пришел список с продуктами: " + productIds.toString());
        return Flux.fromIterable(productIds)
                .flatMap(id -> {
                    Mono<ProductInfo> productMono =
                            productClient.getProductInfo(id)
                                    .doOnNext(p ->
                                            System.out.println("-ReportService- продукт: " + p)
                                    );

                    Mono<DeliveryInfo> deliveryMono =
                            deliveryClient.getDeliveryInfo(id)
                                    .doOnNext(d ->
                                            System.out.println("-ReportService- доставка: " + d)
                                    );


                    return Mono.zip(productMono, deliveryMono)
                            .map(t -> new ReportItem(id, t.getT1().getName(), t.getT2().getStatus()));
                });
    }
}
