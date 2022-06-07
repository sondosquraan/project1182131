package com.example.project.repository;

import com.example.project.entity.Product;
import com.example.project.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Integer> {
}
