package com.example.backend.repository;

import com.example.backend.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {
    List<Stock> findAll();

    Stock findByStockCode(Long stockCode);
}
