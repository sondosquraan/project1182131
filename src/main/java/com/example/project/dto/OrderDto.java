package com.example.project.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class OrderDto {
      private Integer id;
    @NotNull
    @Size(min = 3, max = 250)
    private Integer customerId;
    private Date orderedAt;
}
