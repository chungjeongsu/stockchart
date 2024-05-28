package com.example.backend.controller;

import com.example.backend.DTO.ResponseDTO;
import com.example.backend.DTO.UserRequestDTO;
import com.example.backend.DTO.UserResponseDTO;
import com.example.backend.config.Tool;
import com.example.backend.service.JoinService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/join")
public class JoinContoller {
    private final JoinService joinService;
    private final Tool tool;

    public JoinContoller(JoinService joinService, Tool tool) {
        this.joinService = joinService;
        this.tool = tool;
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> join(@RequestBody @Valid UserRequestDTO userRequestDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            bindingResult.getAllErrors().forEach(error -> errorMessage.append(error.getDefaultMessage()).append("\n"));

            return tool.resErr(errorMessage.toString());
        }
        UserResponseDTO userResponseDTO = joinService.join(userRequestDTO);
        return tool.res(HttpStatus.OK, "회원 가입 되었습니다.", userResponseDTO);
    }
}
