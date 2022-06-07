package com.example.project.controller;

import com.example.project.dto.StockDto;
import com.example.project.exceptions.BadRequest;
import com.example.project.services.StockServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/stock")
public class StockController {
    private final Logger log = LoggerFactory.getLogger(StockController.class);

    private StockServices stockServices;

    // Constructor based  injection
    public StockController(StockServices stockService) {
        this.stockServices = stockService;
    }

    @GetMapping
    public ResponseEntity<List<StockDto>> getAllStock() {
        return ResponseEntity.ok().body(stockServices.getAllStock()); //ResponseEntity represents an HTTP response, including headers, body, and status.
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockDto> getStockById(@PathVariable(name = "id") int id) {
        return ResponseEntity.ok(stockServices.getStockById(id));
    }


    @PostMapping
    public ResponseEntity<StockDto> createStock(@Valid @RequestBody StockDto stockDto) {
        if (stockDto.getId() != null) {
            log.error("Cannot have an ID {}", stockDto);
            throw new BadRequest(OrderController.class.getSimpleName(), "Id");
        }
        return new ResponseEntity<>(stockServices.createStock(stockDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StockDto> updateStock(@Valid @RequestBody StockDto stockDto
            , @PathVariable(name = "id") int id) {
        return new ResponseEntity<>(stockServices.updateStock(stockDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStock(@PathVariable(name = "id") int id) {
        stockServices.deleteStockById(id);
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }
}
