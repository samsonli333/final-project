package com.example.project.stock.data.demo_project_stock_data.controller.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.example.project.stock.data.demo_project_stock_data.controller.DataOperation;
import com.example.project.stock.data.demo_project_stock_data.model.DataQuote;
import com.example.project.stock.data.demo_project_stock_data.model.NewsDTO;
import com.example.project.stock.data.demo_project_stock_data.model.OhlcvHistory;
import com.example.project.stock.data.demo_project_stock_data.model.RealTimeQuoteDTO;
import com.example.project.stock.data.demo_project_stock_data.model.StockQuoteAndProfileDTO;
import com.example.project.stock.data.demo_project_stock_data.service.MarqueeService;
import com.example.project.stock.data.demo_project_stock_data.service.NewsService;
import com.example.project.stock.data.demo_project_stock_data.service.OhlvcService;
import com.example.project.stock.data.demo_project_stock_data.service.RealTimeQuoteService;
import com.example.project.stock.data.demo_project_stock_data.service.StockQuoteService;



@RestController
public class DataController implements DataOperation{

  @Autowired
  private OhlvcService ohlvcService;

  @Autowired
  private StockQuoteService stockQuoteService;

  @Autowired
  private RealTimeQuoteService realTimeQuoteService;

 @Autowired
 private NewsService newsService;

@Autowired
private MarqueeService marqueeService;

  @Override
  public DataQuote getRealTimeData(){
    return this.realTimeQuoteService.getRealTimeData();
  }

  @Override
  public OhlcvHistory getHistoryBySymbol(String symbol,int start) {
     return this.ohlvcService.getHistoryBySymbol(symbol,start);
  }
  

  @Override
  public RealTimeQuoteDTO getRealTimeBySymbol(String symbol){
    return this.realTimeQuoteService.getRealTimeBySymbol(symbol);
  }

  @Override
  public List<NewsDTO> getNews(String symbol){
     return this.newsService.getNews(symbol);
  }

  @Override
  public List<StockQuoteAndProfileDTO> getMarquee(){
    return this.marqueeService.getMarquee();
  }

}
