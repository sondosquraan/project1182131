package com.example.project.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data

public class StockDto {

        @NotNull
        @Size(min = 3, max = 250)
        private Integer id;
        private Integer productId;
        private Integer quantity;
        private Date updateAt;

}
