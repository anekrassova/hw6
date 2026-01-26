package org.example.hw10.controller;

import org.example.hw10.dto.ReportItem;
import org.example.hw10.dto.ReportRequest;
import org.example.hw10.service.ReportService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping
    public Flux<ReportItem> report(@RequestBody ReportRequest request) {
        return reportService.aggregateDeliveryInfo(request.getProductIds());
    }
}
