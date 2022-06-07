package com.example.project.services;
import java.util.List;

import com.example.project.dto.ProductDto;


public interface ProductServices {
    ProductDto createProduct (ProductDto productDto);

    List<ProductDto> getAllProducts();


    ProductDto getProductById(Integer id);

    ProductDto updateProduct(ProductDto productDto, Integer id);

    void deleteProductById(Integer id);

}
