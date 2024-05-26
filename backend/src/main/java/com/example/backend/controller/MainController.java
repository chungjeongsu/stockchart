package com.example.backend.controller;

import com.example.backend.DTO.*;
import com.example.backend.config.Tool;
import com.example.backend.entity.Subscribe;
import com.example.backend.service.MainService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/main")
public class MainController {
    private final Tool tool;
    private final MainService mainService;

    public MainController(Tool tool, MainService mainService) {
        this.tool = tool;
        this.mainService = mainService;
    }

    @GetMapping("/user-info")
    public ResponseEntity<ResponseDTO> getUserInfo(@RequestParam("userId") String userId){
        System.out.println(userId);
        UserResponseDTO userResponseDTO = mainService.getMyInfo(userId);
        return tool.res(HttpStatus.OK, "내 정보입니다.", userResponseDTO);
    }

    @GetMapping("/my-stock-list")
    public ResponseEntity<ResponseDTO> getMyStockList(@RequestParam("userId") String userId){
        MyStockListResponseDTO myStockListResponseDTO = mainService.getMyStockList(userId);
        return tool.res(HttpStatus.OK, "구독주식 정보입니다.", myStockListResponseDTO);
    }

    @PostMapping("/remove-subscribe")
    public ResponseEntity<ResponseDTO> removeSubscribe(@RequestBody SubscribeRequestDTO subscribeRequestDTO){
        StockDTO stockDTO = mainService.removeSubscribe(subscribeRequestDTO);

        return tool.res(HttpStatus.OK, "삭제 주식 정보입니다.", stockDTO);
    }
}
