package com.example.project.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data

public class ProductDto {

        @NotNull
        @Size(min = 3, max = 250)
        private Integer Id;
        private String slug;
        private String name;
        private String reference;
        private boolean stockable;
        private Long price;
        private Long vat;
}
