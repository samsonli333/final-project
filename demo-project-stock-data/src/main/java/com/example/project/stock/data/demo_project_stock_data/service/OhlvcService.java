package com.example.project.stock.data.demo_project_stock_data.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.project.stock.data.demo_project_stock_data.enity.Ohlcv;
import com.example.project.stock.data.demo_project_stock_data.enity.StockSymbol;
import com.example.project.stock.data.demo_project_stock_data.model.OhlcvHistory;
import com.example.project.stock.data.demo_project_stock_data.repository.OhlvRepository;
import com.example.project.stock.data.demo_project_stock_data.repository.StockSymbolRepository;

@Service
public class OhlvcService {

  @Autowired
  private StockSymbolRepository stockSymbolRepository;

  @Autowired
  private OhlvRepository ohlvRepository;

  public OhlcvHistory getHistoryBySymbol(String symbol) {

    StockSymbol stockSymbol = this.stockSymbolRepository.findBySymbol(symbol)
        .orElseThrow(() -> new NullPointerException());      

      List<Ohlcv> ohlcvs = this.ohlvRepository.findByStockSymbol(stockSymbol);
      
      List<Double> open = ohlcvs.stream().map(x -> x.getOpen()).collect(Collectors.toList());
      List<Double> close = ohlcvs.stream().map(x -> x.getClose()).collect(Collectors.toList());
      List<Double> high = ohlcvs.stream().map(x -> x.getHigh()).collect(Collectors.toList());
      List<Double> low = ohlcvs.stream().map( x-> x.getLow()).collect(Collectors.toList());
      List<LocalDate> date = ohlcvs.stream().map(x -> x.getDate()).collect(Collectors.toList());

    return  OhlcvHistory.builder().close(close)
     .date(date)
     .high(high)
     .low(low)
     .close(close)
     .open(open)
     .build();


  }
}
