package com.example.project.stock.data.demo_project_stock_data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.project.stock.data.demo_project_stock_data.enity.Ohlcv;
import com.example.project.stock.data.demo_project_stock_data.enity.StockSymbol;
import java.util.List;


public interface OhlvRepository extends JpaRepository<Ohlcv,Long>{
       public List<Ohlcv> findByStockSymbol(StockSymbol stockSymbol);;
} 
