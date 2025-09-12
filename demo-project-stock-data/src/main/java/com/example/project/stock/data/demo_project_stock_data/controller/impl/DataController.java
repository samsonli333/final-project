package com.example.project.stock.data.demo_project_stock_data.controller.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.example.project.stock.data.demo_project_stock_data.controller.DataOperation;
import com.example.project.stock.data.demo_project_stock_data.model.DataQuote;
import com.example.project.stock.data.demo_project_stock_data.model.OhlcvHistory;
import com.example.project.stock.data.demo_project_stock_data.service.OhlvcService;
import com.example.project.stock.data.demo_project_stock_data.service.StockQuoteService;


@RestController
public class DataController implements DataOperation{
  @Autowired
  private StockQuoteService stockQuoteService;

  @Autowired
  private OhlvcService ohlvcService;

  @Override
  public DataQuote getRealTimeData(){
    return this.stockQuoteService.getRealTimeData();
  }

  @Override
  public OhlcvHistory getHistoryBySymbol(String symbol) {
     return this.ohlvcService.getHistoryBySymbol(symbol);
  }
  

}
