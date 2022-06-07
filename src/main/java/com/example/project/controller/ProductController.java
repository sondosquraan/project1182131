package com.example.project.controller;

import com.example.project.dto.ProductDto;
import com.example.project.exceptions.BadRequest;
import com.example.project.services.ProductServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;



@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final Logger log = LoggerFactory.getLogger(ProductController.class);

    private ProductServices productServices;

    // Constructor based  injection
    public ProductController(ProductServices productService) {
        this.productServices = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.ok().body(productServices.getAllProducts()); //ResponseEntity represents an HTTP response, including headers, body, and status.
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable(name = "id") int id) {
        return ResponseEntity.ok(productServices.getProductById(id));
    }


    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto) {
        if (productDto.getId() != null) {
            log.error("Cannot have an ID {}", productDto);
            throw new BadRequest(ProductController.class.getSimpleName(), "Id");
        }
        return new ResponseEntity<>(productServices.createProduct(productDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@Valid @RequestBody ProductDto productDto
            , @PathVariable(name = "id") int id) {
        return new ResponseEntity<>(productServices.updateProduct(productDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(name = "id") int id) {
        productServices.deleteProductById(id);
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }
}
