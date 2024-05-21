package com.example.backend.controller;

import com.example.backend.DTO.MyStockListResponseDTO;
import com.example.backend.DTO.ResponseDTO;
import com.example.backend.DTO.UserResponseDTO;
import com.example.backend.config.Tool;
import com.example.backend.service.MainService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<ResponseDTO> getUserInfo(HttpSession httpSession){
        String userId = (String) httpSession.getAttribute("userId");
        UserResponseDTO userResponseDTO = mainService.getMyInfo(userId);
        return tool.res(HttpStatus.OK, "내 정보입니다.", userResponseDTO);
    }

    @GetMapping("/my-stock-list")
    public ResponseEntity<ResponseDTO> getMyStockList(HttpSession httpSession){
        String userId = (String) httpSession.getAttribute("userId");
        MyStockListResponseDTO myStockListResponseDTO = mainService.getMyStockList(userId);
        return tool.res(HttpStatus.OK, "구독주식 정보입니다.", myStockListResponseDTO);
    }
}
