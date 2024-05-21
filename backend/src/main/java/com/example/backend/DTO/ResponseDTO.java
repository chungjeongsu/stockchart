package com.example.backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class ResponseDTO {
    private HttpStatus code;
    private String msg;
    private Object obj;
}
