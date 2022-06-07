package com.example.project.controller;

import com.example.project.dto.Product_OrderDto;
import com.example.project.exceptions.BadRequest;
import com.example.project.services.Product_OrderServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/product_order")
public class Product_OrderController {
    private final Logger log = LoggerFactory.getLogger(Product_OrderController.class);

    private Product_OrderServices product_orderServices;

    // Constructor based  injection
    public Product_OrderController(Product_OrderServices product_orderService) {
        this.product_orderServices = product_orderService;
    }

    @GetMapping
    public ResponseEntity<List<Product_OrderDto>> getAllProducts_Order() {
        return ResponseEntity.ok().body(product_orderServices.getAllProducts_Order()); //ResponseEntity represents an HTTP response, including headers, body, and status.
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product_OrderDto> getProduct_OrderById(@PathVariable(name = "id") int id) {
        return ResponseEntity.ok(product_orderServices.getProduct_OrderById(id));
    }


    @PostMapping
    public ResponseEntity<Product_OrderDto> createProduct_Order(@Valid @RequestBody Product_OrderDto product_orderDto) {
        if (product_orderDto.getOrderId() != null) {
            log.error("Cannot have an ID {}", product_orderDto);
            throw new BadRequest(Product_OrderController.class.getSimpleName(), "Id");
        }
        return new ResponseEntity<>(product_orderServices.createProduct_Order(product_orderDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product_OrderDto> updateProduct_Order(@Valid @RequestBody Product_OrderDto product_orderDto
            , @PathVariable(name = "id") int id) {
        return new ResponseEntity<>(product_orderServices.updateProduct_Order(product_orderDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct_Order(@PathVariable(name = "id") int id) {
        product_orderServices.deleteProduct_OrderById(id);
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }
}
