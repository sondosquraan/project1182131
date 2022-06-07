package com.example.project.controller;

import com.example.project.dto.OrderDto;
import com.example.project.exceptions.BadRequest;
import com.example.project.services.OrderServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final Logger log = LoggerFactory.getLogger(OrderController.class);

    private OrderServices orderServices;

    // Constructor based  injection
    public OrderController( OrderServices orderService) {
        this.orderServices = orderService;
    }

    @GetMapping
    public ResponseEntity<List< OrderDto>> getAllOrder() {
        return ResponseEntity.ok().body( orderServices.getAllOrder()); //ResponseEntity represents an HTTP response, including headers, body, and status.
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable(name = "id") int id) {
        return ResponseEntity.ok(orderServices.getOrderById(id));
    }


    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@Valid @RequestBody OrderDto orderDto) {
        if (orderDto.getId() != null) {
            log.error("Cannot have an ID {}", orderDto);
            throw new BadRequest(OrderController.class.getSimpleName(), "Id");
        }
        return new ResponseEntity<>(orderServices.createOrder(orderDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> updateOrder(@Valid @RequestBody OrderDto orderDto
            , @PathVariable(name = "id") int id) {
        return new ResponseEntity<>(orderServices.updateOrder(orderDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable(name = "id") int id) {
        orderServices.deleteOrderById(id);
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }
}
