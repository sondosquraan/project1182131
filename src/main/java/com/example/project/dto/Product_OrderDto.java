package com.example.project.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data

public class Product_OrderDto {

        @NotNull
        @Size(min = 3, max = 250)
        private Integer productId;
        private Integer orderId;
        private Integer quantity;
        private Long price;
        private Long vat;
}