package com.example.project.stock.data.demo_project_stock_data.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.project.stock.data.demo_project_stock_data.enity.StockSymbol;
import com.example.project.stock.data.demo_project_stock_data.repository.StockSymbolRepository;

@Service
public class StockSymbolService {

    @Autowired
    private StockSymbolRepository stockSymbolRepository;

    public List<StockSymbol> findAllStockSymbol(){
      return this.stockSymbolRepository.findAll(Sort.by(Sort.Direction.ASC,"symbol"));
    }
}
