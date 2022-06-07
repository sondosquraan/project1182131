package com.example.project.services.imp;

import com.example.project.dto.OrderDto;
import com.example.project.entity.Order;
import com.example.project.entity.Product;
import com.example.project.exceptions.ApiRequestExeption;
import com.example.project.repository.OrderRepository;
import com.example.project.services.OrderServices;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service //To enable this class for component scanning
public class OrderServicesImp implements OrderServices {

    private OrderRepository orderRepository;

    public OrderServicesImp(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }



    @Override
    public OrderDto createOrder(OrderDto orderDto) {

        // convert DTO to entity
        Order order = mapToEntity(orderDto);
        Order neworder = orderRepository.save(order);

        // convert entity to DTO
        OrderDto orderResponse = mapToDTO(neworder);
        return orderResponse;
    }



    @Override
    public List<OrderDto> getAllOrder() {
        List<Order> order =orderRepository.findAll();
        return order.stream().map(orders -> mapToDTO(orders)).collect(Collectors.toList());        }

    @Override
    public OrderDto getOrderById(Integer id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ApiRequestExeption("Couldn't find the order"));
        return mapToDTO(order);
    }

    @Override
    public OrderDto updateOrder(OrderDto orderDto, Integer id) {
        //get product by id from the database
        Order order = orderRepository.findById(id).orElseThrow(() -> new ApiRequestExeption("Couldn't find the order"));
       order.setId(orderDto.getId());


        Order updateOrder= orderRepository.save(order);
        return mapToDTO(updateOrder);
    }

    @Override
    public void deleteOrderById(Integer id) {
        // get Product by id from the dOatabase
        Order order = orderRepository.findById(id).orElseThrow(() -> new ApiRequestExeption("Couldn't find the order"));
        orderRepository.delete(order);
    }

    // convert Entity into DTO
    private OrderDto mapToDTO(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());


        return orderDto;
    }

    // convert DTO to entity
    private Order mapToEntity(OrderDto orderDto) {
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setCustomerId(orderDto.getId());
        order.setId(orderDto.getId());
        return order;
    }
}

