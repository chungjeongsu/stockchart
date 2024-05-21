package com.example.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class StockDTO {
    private Long stockCode;
    private String stockName;
    private Long stockPrice;
    private Long stockAmount;
}
