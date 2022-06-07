package com.example.project.services;
import java.util.List;

import com.example.project.dto.OrderDto;


public interface OrderServices {
    OrderDto createOrder (OrderDto orderDto);

    List<OrderDto> getAllOrder();


    OrderDto getOrderById(Integer id);

    OrderDto updateOrder(OrderDto orderDto, Integer id);

    void deleteOrderById(Integer id);

}
