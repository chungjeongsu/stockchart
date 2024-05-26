package com.example.backend.controller;

import com.example.backend.DTO.ResponseDTO;
import com.example.backend.DTO.UserRequestDTO;
import com.example.backend.DTO.UserResponseDTO;
import com.example.backend.config.Tool;
import com.example.backend.service.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final LoginService loginService;
    private final Tool tool;

    public LoginController(LoginService loginService, Tool tool) {
        this.loginService = loginService;
        this.tool = tool;
    }
    @PostMapping
    public ResponseEntity<ResponseDTO> login(@RequestBody UserRequestDTO userRequestDTO){
        UserResponseDTO userResponseDTO = loginService.login(userRequestDTO);
        System.out.println(userResponseDTO.toString());
        return tool.res(HttpStatus.OK, "로그인 성공", userResponseDTO);
    }
}
