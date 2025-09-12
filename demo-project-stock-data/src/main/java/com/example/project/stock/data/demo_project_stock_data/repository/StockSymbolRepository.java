package com.example.project.stock.data.demo_project_stock_data.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.project.stock.data.demo_project_stock_data.enity.StockSymbol;

@Repository
public interface StockSymbolRepository extends JpaRepository<StockSymbol,Long>{

    public Optional<StockSymbol> findBySymbol(String x);
} 
