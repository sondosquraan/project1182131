package com.example.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table
@IdClass(Product_OrderPrimarykey.class)
public class Product_Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId" , referencedColumnName = "id")
    private Integer productId;
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId" , referencedColumnName = "id")
    private Integer orderId;
    @Column
    private Integer quantity;
    @Column
    private Long price;
    @Column
    private Long vat;

}
