package com.example.backend.controller;

import com.example.backend.DTO.ResponseDTO;
import com.example.backend.DTO.StockDTO;
import com.example.backend.DTO.StockListResponseDTO;
import com.example.backend.DTO.SubscribeRequestDTO;
import com.example.backend.config.Tool;
import com.example.backend.service.StockService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stockList")
public class StockListController {
    private final Tool tool;
    private final StockService stockService;

    public StockListController(Tool tool, StockService stockService) {
        this.tool = tool;
        this.stockService = stockService;
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> getStockList(){
        StockListResponseDTO stockListResponseDTO = stockService.getStockList();
        return tool.res(HttpStatus.OK, "주식 목록입니다.", stockListResponseDTO);
    }

    @PostMapping ("/subscribe")
    public ResponseEntity<ResponseDTO> subscribe(@RequestBody SubscribeRequestDTO subscribeRequestDTO){
        StockDTO stockDTO = stockService.subscribe(subscribeRequestDTO);
        return tool.res(HttpStatus.OK,"주식 구독 성공", stockDTO);
    }
}
