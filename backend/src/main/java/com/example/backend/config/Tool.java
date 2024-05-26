package com.example.backend.config;

import com.example.backend.DTO.ResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Tool {
    private final ModelMapper modelMapper;

    public Tool(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ResponseEntity<ResponseDTO> res(HttpStatus code, String msg, Object data){
        return ResponseEntity.ok().body(new ResponseDTO(code, msg, data));
    }
    public ResponseEntity<ResponseDTO> resWithCookie(HttpStatus code, String msg, Object data, ResponseCookie cookie){
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE,cookie.toString()).body(new ResponseDTO(code, msg, data));
    }
    public ResponseEntity<ResponseDTO> resErr(String msg){
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.BAD_REQUEST,msg, null));
    }
    public ResponseEntity<ResponseDTO> resCustom(HttpStatus code, String msg, Object data){
        return ResponseEntity.ok().body(new ResponseDTO(code, msg, data));
    }
    public <S, T> List<T> convert(List<S> list, Class<T> targetClass) {
        return list.stream()
                .map(value -> modelMapper.map(value, targetClass))
                .collect(Collectors.toList());
    }
}
