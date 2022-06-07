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
//Generates getters for all fields, a useful toString method, and hashCode and equals implementations that check all non-transient fields
@AllArgsConstructor   //automatically generates a constructor with a parameter for each field in your class
@NoArgsConstructor     // generates a constructor with no parameter
@Entity                 // specifies that the class is an entity and is mapped to a database table

@Table
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //1*(many)
    @Column
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId" , referencedColumnName = "id")

    private Integer customerId;

    @Column
    private Date product_orderedAt;

    @JsonIgnore
    @OneToMany(mappedBy = "orderId")
    private Set<Product_Order> Product_Order = new HashSet<>();

}