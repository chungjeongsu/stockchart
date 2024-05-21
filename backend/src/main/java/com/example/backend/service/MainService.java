package com.example.backend.service;

import com.example.backend.DTO.MyStockListResponseDTO;
import com.example.backend.DTO.StockDTO;
import com.example.backend.DTO.UserResponseDTO;
import com.example.backend.entity.Stock;
import com.example.backend.entity.Subscribe;
import com.example.backend.entity.User;
import com.example.backend.repository.SubscribeRepository;
import com.example.backend.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MainService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final SubscribeRepository subscribeRepository;
    public MainService(ModelMapper modelMapper, UserRepository userRepository, SubscribeRepository subscribeRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.subscribeRepository = subscribeRepository;
    }

    @Transactional(readOnly = true)
    public MyStockListResponseDTO getMyStockList(String userId){
        List<Subscribe> findSubscribes = subscribeRepository.findAllByUser_UserId(userId);
        List<StockDTO> stockDTOS = findSubscribes.stream()
                .map(findSubscribe->modelMapper.map(findSubscribe.getStock(), StockDTO.class))
                .collect(Collectors.toList());
        return new MyStockListResponseDTO(stockDTOS);
    }
    @Transactional(readOnly = true)
    public UserResponseDTO getMyInfo(String userId){
        User findUser = userRepository.findByUserId(userId);
        return modelMapper.map(findUser, UserResponseDTO.class);
    }
}
