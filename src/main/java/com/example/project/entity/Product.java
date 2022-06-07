package com.example.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    @Column
    private String slug;
    @Column
    private String name;
    @Column
    private String reference;
    @Column
    private boolean stockable;
    @Column
    private Long price;
    @Column
    private Long vat;

    @JsonIgnore
    @OneToMany(mappedBy = "productId")
    private Set<Product_Order> Product_Order = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "productId")
    private Set<Stock> Stock = new HashSet<>();

}
