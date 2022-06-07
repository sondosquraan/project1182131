package com.example.project.services.imp;

import com.example.project.dto.StockDto;
import com.example.project.entity.Stock;
import com.example.project.exceptions.ApiRequestExeption;
import com.example.project.repository.StockRepository;
import com.example.project.services.StockServices;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service // to let the api know that this page is responsible for services
public class StockServicesImp implements StockServices {
    private StockRepository stockRepository;

    public StockServicesImp(StockRepository stockRepository){this.stockRepository=stockRepository;}
    @Override
    public StockDto createStock(StockDto StockDto) {

        Stock stock = mapToEntity(StockDto);
        Stock newStock = stockRepository.save(stock);

        StockDto stockDto = mapToDTO(newStock);
        return stockDto;
    }

    @Override
    public List<StockDto> getAllStock() {
        List<Stock> stocks = stockRepository.findAll();
        return stocks.stream().map(stock -> mapToDTO(stock)).collect(Collectors.toList());
    }

    @Override
    public StockDto getStockById(Integer id) {
        Stock stock = stockRepository.findById(id).orElseThrow(()->  new ApiRequestExeption("Couldn't find the stock"));
        return mapToDTO(stock);
    }

    @Override
    public StockDto updateStock(StockDto StockDto, Integer id) {
        Stock stock = stockRepository.findById(id).orElseThrow(()-> new ApiRequestExeption("Couldn't find the stock"));
        stock.setId(StockDto.getId());
        stock.setProductId(StockDto.getProductId());
        stock.setQuantity(StockDto.getQuantity());
        stock.setUpdateAt(StockDto.getUpdateAt());
        Stock newStock = stockRepository.save(stock);
        return mapToDTO(newStock);
    }

    @Override
    public void deleteStockById(Integer id) {
        Stock stock = stockRepository.findById(id).orElseThrow(()-> new ApiRequestExeption("Couldn't find the stock"));
        stockRepository.delete(stock);
    }
    // convert the data from an entity to DTO
    private StockDto mapToDTO(Stock stock){
        StockDto stockDto = new StockDto();
        stockDto.setId(stock.getId());
        stockDto.setProductId(stock.getProductId());
        stockDto.setQuantity(stock.getQuantity());
        stockDto.setUpdateAt(stock.getUpdateAt());


        return stockDto;
    }
    // convert the data from DTO to entity
    private Stock mapToEntity(StockDto stockDto){
        Stock stock = new Stock();
        stock.setId(stockDto.getId());
        stock.setProductId(stockDto.getProductId());
        stock.setQuantity(stockDto.getQuantity());
        stock.setUpdateAt(stockDto.getUpdateAt());
        return stock;
    }
}
