package org.example.hw10.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class ReportRequest {
    private List<Long> productIds;
}
