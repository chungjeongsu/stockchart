package com.example.backend.service;

import com.example.backend.DTO.UserRequestDTO;
import com.example.backend.DTO.UserResponseDTO;
import com.example.backend.entity.User;
import com.example.backend.exception.LoginFailedException;
import com.example.backend.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public LoginService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional(readOnly = true)
    public UserResponseDTO login(UserRequestDTO userRequestDTO){
        User user = userRepository.findByUserId(userRequestDTO.getUserId());
        if(user == null){
            throw new EntityExistsException("유저가 없습니다.");
        }
        if(!user.getUserPw().equals(userRequestDTO.getUserPw())){
            throw new LoginFailedException("아이디 또는 비밀번호가 틀립니다.");
        }
        //유저존재, 아이디 비밀번호 맞는 경우
        return modelMapper.map(user, UserResponseDTO.class);
    }
}

