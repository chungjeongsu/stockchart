package com.example.backend.exception.controller;

import com.example.backend.DTO.ResponseDTO;
import com.example.backend.config.Tool;
import com.example.backend.exception.LoginFailedException;
import com.example.backend.exception.UserAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {
    private final Tool tool;

    public ExceptionController(Tool tool) {
        this.tool = tool;
    }

    @ExceptionHandler
    public ResponseEntity<ResponseDTO> loginFailedException(LoginFailedException ex){
        return tool.res(HttpStatus.CONFLICT, "비밀번호가 맞지 않습니다.", ex);
    }
    @ExceptionHandler
    public ResponseEntity<ResponseDTO> userAlreadyExistException(UserAlreadyExistException ex){
        return tool.res(HttpStatus.CONFLICT, "중복된 아이디!", ex);
    }
}
