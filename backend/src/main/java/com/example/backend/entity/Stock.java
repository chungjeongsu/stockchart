package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "stock")
@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class Stock {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_code")
    private Long stockCode;

    @Column(name = "stock_name")
    private String stockName;

    @Column(name = "stock_price")
    private Long stockPrice;

    @Column(name = "stock_amount")
    private Long stockAmount;
}
