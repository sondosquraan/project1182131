package com.example.project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data  //Generates getters for all fields, a useful toString method, and hashCode and equals implementations that check all non-transient fields
@AllArgsConstructor   //automatically generates a constructor with a parameter for each field in your class
@NoArgsConstructor     // generates a constructor with no parameter
@Entity                 // specifies that the class is an entity and is mapped to a database table
@Table(name = "customer_tbl", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name"})
})

public class Customer {
    @Id   //declare the primary key
    @GeneratedValue(
            strategy = GenerationType.IDENTITY //indicates that the persistence provider must assign primary keys for the entity using a database identity column.This means they are auto-incremented
    )
    private Integer id;

    @Column(name = "name", nullable = false)
    private String firstName;

    @Column
    private String lastName;

    @Column
    private Date bornAt;

    //(1)*many
    @JsonIgnore
    @OneToMany(mappedBy = "customerId")
    private Set<Order> Order = new HashSet<>();
}
