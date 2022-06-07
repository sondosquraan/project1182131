package com.example.project.services;
import java.util.List;

import com.example.project.dto.StockDto;


public interface StockServices {
    StockDto createStock (StockDto stockDto);

    List<StockDto> getAllStock();


    StockDto getStockById(Integer id);

    StockDto updateStock(StockDto stockDto, Integer id);

    void deleteStockById(Integer id);

}