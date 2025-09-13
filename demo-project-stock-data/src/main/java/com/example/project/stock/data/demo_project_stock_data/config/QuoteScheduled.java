package com.example.project.stock.data.demo_project_stock_data.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import com.example.project.stock.data.demo_project_stock_data.repository.StockSymbolRepository;

public class QuoteScheduled {
  
  @Autowired
  private StockSymbolRepository stockSymbolRepository;

@Scheduled(fixedDelay = 5000)
  public void runTask(){
       
  }
}
