package com.example.project.stock.data.demo_project_stock_data.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;
import com.example.project.stock.data.demo_project_stock_data.repository.RealTimeQuoteRepository;
import com.example.project.stock.data.demo_project_stock_data.repository.StockSymbolRepository;

public class QuoteScheduled {
  
  @Autowired
  private StockSymbolRepository stockSymbolRepository;

@Autowired
  private RealTimeQuoteRepository realTimeQuoteRepository;


  @Autowired
  private RestTemplate restTemplate;

@Scheduled(fixedDelay = 5000)
  public void runTask(){
        
  }
}
