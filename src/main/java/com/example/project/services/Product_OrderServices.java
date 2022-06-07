package com.example.project.services;
import java.util.List;

import com.example.project.dto.Product_OrderDto;


public interface Product_OrderServices {
    Product_OrderDto createProduct_Order (Product_OrderDto product_orderDto);

    List<Product_OrderDto> getAllProducts_Order();


    Product_OrderDto getProduct_OrderById(Integer id);

    Product_OrderDto updateProduct_Order(Product_OrderDto product_orderDto, Integer id);

    void deleteProduct_OrderById(Integer id);

}
