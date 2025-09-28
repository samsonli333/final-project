package com.example.project.stock.data.demo_project_stock_data.controller;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.project.stock.data.demo_project_stock_data.model.DataQuote;
import com.example.project.stock.data.demo_project_stock_data.model.NewsDTO;
import com.example.project.stock.data.demo_project_stock_data.model.OhlcvHistory;
import com.example.project.stock.data.demo_project_stock_data.model.RealTimeQuoteDTO;
import com.example.project.stock.data.demo_project_stock_data.model.StockQuoteAndProfileDTO;





@RequestMapping(value = "/api")
public interface DataOperation {
  
@GetMapping(value = "/data")
public DataQuote getRealTimeData();


@GetMapping(value = "/history")
public OhlcvHistory getHistoryBySymbol(@RequestParam(name = "symbol") String symbol,@RequestParam(name = "start",defaultValue = "0") int start);


@GetMapping(value = "/companydata")
public RealTimeQuoteDTO getRealTimeBySymbol(@RequestParam(name = "symbol") String symbol);

@GetMapping(value = "/news")
public List<NewsDTO> getNews(@RequestParam(name = "symbol") String symbol);

@GetMapping(value = "marquee")
public List<StockQuoteAndProfileDTO> getMarquee();



}
