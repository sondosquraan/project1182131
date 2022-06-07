package com.example.project.services.imp;

import com.example.project.dto.CustomerDto;
import com.example.project.entity.Customer;
import com.example.project.exceptions.ApiRequestExeption;
import com.example.project.repository.CustomerRepository;
import com.example.project.services.CustomerServices;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service // to let the api know that this page is responsible for services
public class CustomerServicesImp implements CustomerServices {
    private CustomerRepository customerRepository;

    public CustomerServicesImp(CustomerRepository customerRepository){this.customerRepository=customerRepository;}
    @Override
    public CustomerDto createCustomer(CustomerDto CustomerDto) {

        Customer customer = mapToEntity(CustomerDto);
        Customer newCustomer = customerRepository.save(customer);

        CustomerDto customerDto = mapToDTO(newCustomer);
        return customerDto;
    }

    @Override
    public List<CustomerDto> getAllCustomer() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(customer1 -> mapToDTO(customer1)).collect(Collectors.toList());
    }

    @Override
    public CustomerDto getCustomerById(Integer id) {
        Customer customer = customerRepository.findById(id).orElseThrow(()->  new ApiRequestExeption("Couldn't find the customer"));
        return mapToDTO(customer);
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto CustomerDto, Integer id) {
        Customer customer = customerRepository.findById(id).orElseThrow(()-> new ApiRequestExeption("Couldn't find the customer"));
        customer.setId(CustomerDto.getId());
        customer.setFirstName(CustomerDto.getFirstName());
        customer.setLastName(CustomerDto.getLastName());
        customer.setBornAt(CustomerDto.getBornAt());
        Customer newCustomer = customerRepository.save(customer);
        return mapToDTO(newCustomer);
    }

    @Override
    public void deleteCustomerById(Integer id) {
        Customer customer = customerRepository.findById(id).orElseThrow(()-> new ApiRequestExeption("Couldn't find the customer"));
        customerRepository.delete(customer);
    }
    // convert the data from an entity to DTO
    private CustomerDto mapToDTO(Customer customer){
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getId());
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customer.getLastName());
        customerDto.setBornAt(customer.getBornAt());

        return customerDto;
    }
    // convert the data from DTO to entity
    private Customer mapToEntity(CustomerDto customerDto){
        Customer customer = new Customer();
        customer.setId(customerDto.getId());
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setBornAt(customerDto.getBornAt());

        return customer;
    }
}
