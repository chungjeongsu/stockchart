package com.example.backend.service;

import com.example.backend.DTO.StockDTO;
import com.example.backend.DTO.StockListResponseDTO;
import com.example.backend.DTO.SubscribeRequestDTO;
import com.example.backend.entity.Stock;
import com.example.backend.entity.Subscribe;
import com.example.backend.entity.User;
import com.example.backend.repository.StockRepository;
import com.example.backend.repository.SubscribeRepository;
import com.example.backend.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockService {
    private final StockRepository stockRepository;
    private final ModelMapper modelMapper;
    private final SubscribeRepository subscribeRepository;
    private final UserRepository userRepository;

    public StockService(StockRepository stockRepository, ModelMapper modelMapper, SubscribeRepository subscribeRepository, UserRepository userRepository) {
        this.stockRepository = stockRepository;
        this.modelMapper = modelMapper;
        this.subscribeRepository = subscribeRepository;
        this.userRepository = userRepository;
    }
    
    @Transactional(readOnly = true)
    public StockListResponseDTO getStockList(){
        List<Stock> stocks = stockRepository.findAll();
        List<StockDTO> stockDTOs = stocks.stream()
                .map(stock -> modelMapper.map(stock, StockDTO.class))
                .collect(Collectors.toList());
        return new StockListResponseDTO(stockDTOs);
    }

    @Transactional
    public StockDTO subscribe(SubscribeRequestDTO subscribeRequestDTO) {
        Stock findStock = stockRepository.findByStockCode(subscribeRequestDTO.getStockCode());
        System.out.println(subscribeRequestDTO.getUserId()+"\n\n\n\n\n\n\n\n\n\n\n\n");
        User findUser = userRepository.findByUserId(subscribeRequestDTO.getUserId());
        Subscribe saveSubscribe = new Subscribe();
        saveSubscribe.setStock(findStock);
        saveSubscribe.setUser(findUser);

        subscribeRepository.save(saveSubscribe);

        return modelMapper.map(findStock, StockDTO.class);
    }
}
