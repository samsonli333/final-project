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

  public OhlcvHistory getHistoryBySymbol(String symbol,int start) {
    LocalDate past1Year = LocalDate.now().minusYears(1);
    LocalDate past2Year = LocalDate.now().minusYears(2);
    LocalDate pastTime = start != 0 ? start == 1 ? past1Year : past2Year : LocalDate.now().minusYears(5); 
    StockSymbol stockSymbol = this.stockSymbolRepository.findBySymbol(symbol)
        .orElseThrow(() -> new NullPointerException());      

      List<Ohlcv> ohlcvs = this.ohlvRepository.findByStockSymbol(stockSymbol)
      .stream().filter(x -> x.getDate()
      .isAfter(pastTime) || x.getDate().isEqual(pastTime))
      .collect(Collectors.toList());


      
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
