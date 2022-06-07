package com.example.project.services;
import java.util.List;

import com.example.project.dto.CustomerDto;


public interface CustomerServices {
    CustomerDto createCustomer (CustomerDto customerDto);

    List<CustomerDto> getAllCustomer();

    CustomerDto getCustomerById(Integer id);

    CustomerDto updateCustomer(CustomerDto customerDto, Integer id);

    void deleteCustomerById(Integer id);



}