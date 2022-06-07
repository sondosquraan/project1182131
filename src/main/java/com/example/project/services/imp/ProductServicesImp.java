package com.example.project.services.imp;

import com.example.project.dto.ProductDto;
import com.example.project.entity.Product;
import com.example.project.exceptions.ApiRequestExeption;
import com.example.project.repository.ProductRepository;
import com.example.project.services.ProductServices;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service //To enable this class for component scanning
public class ProductServicesImp implements ProductServices {

    private ProductRepository productRepository;

    public ProductServicesImp(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }



    @Override
    public ProductDto createProduct(ProductDto productDto) {

        // convert DTO to entity
        Product product = mapToEntity(productDto);
        Product newProduct = productRepository.save(product);

        // convert entity to DTO
        ProductDto productResponse = mapToDTO(newProduct);
        return productResponse;
    }



    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products =productRepository.findAll();
        return products.stream().map(product -> mapToDTO(product)).collect(Collectors.toList());        }

    @Override
    public ProductDto getProductById(Integer id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ApiRequestExeption("Couldn't find the product"));
        return mapToDTO(product);
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto, Integer id) {
        //get product by id from the database
        Product product = productRepository.findById(id).orElseThrow(() -> new ApiRequestExeption("Couldn't find the product"));
        product.setId(productDto.getId());
        product.setSlug(productDto.getSlug());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setVat(productDto.getVat());
        product.setReference(productDto.getReference());
        product.setStockable(productDto.isStockable());

        Product updatedProduct= productRepository.save(product);
        return mapToDTO(updatedProduct);
    }

    @Override
    public void deleteProductById(Integer id) {
        // get Product by id from the database
        Product product = productRepository.findById(id).orElseThrow(() -> new ApiRequestExeption("Couldn't find the product"));
        productRepository.delete(product);
    }

    // convert Entity into DTO
    private ProductDto mapToDTO(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setSlug(product.getSlug());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setVat(product.getVat());
        productDto.setReference(product.getReference());
        productDto.setStockable(product.isStockable());

        return productDto;
    }

    // convert DTO to entity
    private Product mapToEntity(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setSlug(productDto.getSlug());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setVat(productDto.getVat());
        product.setReference(productDto.getReference());
        product.setStockable(productDto.isStockable());
        return product;
    }
}

