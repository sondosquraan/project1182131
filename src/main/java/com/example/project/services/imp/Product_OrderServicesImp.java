/*package com.example.project.services.imp;

import com.example.project.dto.Product_OrderDto;
import com.example.project.entity.Product_Order;
import com.example.project.exceptions.ApiRequestExeption;
import com.example.project.repository.Product_OrderRepository;
import com.example.project.services.Product_OrderServices;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service //To enable this class for component scanning
public class Product_OrderServicesImp implements Product_OrderServices {

    private Product_OrderRepository product_orderRepository;

    public Product_OrderServicesImp(Product_OrderRepository product_orderRepository) {
        this.product_orderRepository = product_orderRepository;
    }



    @Override
    public Product_OrderDto createProduct_Order(Product_OrderDto product_orderDto) {

        // convert DTO to entity
        Product_Order product_order = mapToEntity(product_orderDto);
        Product_Order newProduct_Order = product_orderRepository.save(product_order);

        // convert entity to DTO
        Product_OrderDto product_orderResponse = mapToDTO(newProduct_Order);
        return product_orderResponse;
    }


    @Override
    public List<Product_OrderDto> getAllProducts_Order() {
        List<Product_Order> products_order =product_orderRepository.findAll();
        return products_order.stream().map(product_order -> mapToDTO(product_order)).collect(Collectors.toList());        }

    @Override
    public Product_OrderDto getProduct_OrderById(Integer id) {
        Product_Order product_order = product_orderRepository.findById(id).orElseThrow(() -> new ApiRequestExeption("Couldn't find the product_order"));
        return mapToDTO(product_order);
    }

    @Override
    public Product_OrderDto updateProduct_Order(Product_OrderDto product_orderDto, Integer id) {
        //get product by id from the database
        Product_Order product_order = product_orderRepository.findById(id).orElseThrow(()  -> new ApiRequestExeption("Couldn't find the product_order"));
        product_order.setProductId(product_orderDto.getProductId());
        product_order.setOrderId(product_orderDto.getOrderId());
        product_order.setQuantity(product_orderDto.getQuantity());
        product_order.setPrice(product_orderDto.getPrice());
        product_order.setVat(product_orderDto.getVat());



        Product_Order updatedProduct_Order= product_orderRepository.save(product_order);
        return mapToDTO(updatedProduct_Order);
    }

    @Override
    public void deleteProduct_OrderById(Integer id) {
        // get Product by id from the database
        Product_Order product_order = product_orderRepository.findById(id).orElseThrow(()  -> new ApiRequestExeption("Couldn't find the product_order"));
        product_orderRepository.delete(product_order);
    }

    // convert Entity into DTO
    private Product_OrderDto mapToDTO(Product_Order product_order) {
        Product_OrderDto product_orderDto = new Product_OrderDto();
        product_orderDto.setProductId(product_order.getProductId());
        product_orderDto.setOrderId(product_order.getOrderId());
        product_orderDto.setPrice(product_order.getPrice());
        product_orderDto.setVat(product_order.getVat());
        product_orderDto.setQuantity(product_order.getQuantity());



        return product_orderDto;
    }

    // convert DTO to entity
    private Product_Order mapToEntity(Product_OrderDto product_orderDto) {
        Product_Order product_order = new Product_Order();
        product_order.setProductId(product_orderDto.getProductId());
        product_order.setOrderId(product_orderDto.getOrderId());
        product_order.setQuantity(product_orderDto.getQuantity());
        product_order.setPrice(product_orderDto.getPrice());
        product_order.setVat(product_orderDto.getVat());
        return product_order;
    }
}
*/
