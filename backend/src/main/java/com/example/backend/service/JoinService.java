package com.example.backend.service;

import com.example.backend.DTO.UserRequestDTO;
import com.example.backend.DTO.UserResponseDTO;
import com.example.backend.entity.User;
import com.example.backend.exception.UserAlreadyExistException;
import com.example.backend.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JoinService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    public JoinService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public UserResponseDTO join(UserRequestDTO userRequestDTO){
        User findUser = userRepository.findByUserId(userRequestDTO.getUserId());
        if(findUser != null){
            throw new UserAlreadyExistException("중복된 아이디입니다.");
        }


        User saveUser = modelMapper.map(userRequestDTO, User.class);
        saveUser = userRepository.save(saveUser);
        return modelMapper.map(saveUser, UserResponseDTO.class);
    }
}
