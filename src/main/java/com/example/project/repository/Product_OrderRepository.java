package com.example.project.repository;

import com.example.project.entity.Product_Order;
import com.example.project.entity.Product_OrderPrimarykey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Product_OrderRepository extends JpaRepository<Product_Order, Product_OrderPrimarykey> {
}
