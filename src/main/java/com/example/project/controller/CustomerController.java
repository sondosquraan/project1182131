package com.example.project.controller;

import com.example.project.dto.CustomerDto;
import com.example.project.exceptions.BadRequest;
import com.example.project.services.CustomerServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private final Logger log = LoggerFactory.getLogger(CustomerController.class);

    private CustomerServices customerServices;

    // Constructor based  injection
    public CustomerController(CustomerServices customerService) {this.customerServices = customerService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomer() {
        return ResponseEntity.ok().body(customerServices.getAllCustomer()); //ResponseEntity represents an HTTP response, including headers, body, and status.
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable(name = "id") int id) {
        return ResponseEntity.ok(customerServices.getCustomerById(id));
    }


    @PostMapping
    public ResponseEntity<CustomerDto> createcustomer(@Valid @RequestBody CustomerDto customerDto) {
        if (customerDto.getId() != null) {
            log.error("Cannot have an ID {}", customerDto);
            throw new BadRequest(CustomerController.class.getSimpleName(), "Id");
        }
        return new ResponseEntity<>(customerServices.createCustomer(customerDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@Valid @RequestBody CustomerDto customerDto
            , @PathVariable(name = "id") int id) {
        return new ResponseEntity<>(customerServices.updateCustomer(customerDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable(name = "id") int id) {
        customerServices.deleteCustomerById(id);
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }
}
