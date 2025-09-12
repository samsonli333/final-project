package com.example.project.stock.data.demo_project_stock_data.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.project.stock.data.demo_project_stock_data.model.DataQuote;
import com.example.project.stock.data.demo_project_stock_data.model.OhlcvHistory;




@RequestMapping(value = "/api")
public interface DataOperation {
  
@GetMapping(value = "/data")
public DataQuote getRealTimeData();


@GetMapping(value = "/history")
public OhlcvHistory getHistoryBySymbol(@RequestParam(name = "symbol") String symbol);

}
