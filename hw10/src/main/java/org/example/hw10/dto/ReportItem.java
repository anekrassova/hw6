package org.example.hw10.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReportItem {
    private Long productId;
    private String name;
    private String status;
}
