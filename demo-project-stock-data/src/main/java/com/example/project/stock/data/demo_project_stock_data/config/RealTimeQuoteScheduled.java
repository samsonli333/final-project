package com.example.project.stock.data.demo_project_stock_data.config;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.example.project.stock.data.demo_project_stock_data.model.StockQuoteDTO;
import com.example.project.stock.data.demo_project_stock_data.repository.RealTimeQuoteRepository;

@Component
public class RealTimeQuoteScheduled {

  private int counter = 0;

  @Autowired
  private RestTemplate restTemplate;

  @Scheduled(fixedDelay = 10000)
  public void runTask() {

    Runnable task = () -> {
      String[] symbols = RealTimeQuoteRepository.getSymbols();

      if (symbols.length > 0) {

        String currentSymbol = symbols[this.counter % symbols.length];

        String url = "http://localhost:8081/api/data?symbol=" + currentSymbol;

        StockQuoteDTO[] stockQuoteDTO =
            this.restTemplate.getForObject(url, StockQuoteDTO[].class);
        StockQuoteDTO[] stockQuote =
            Optional.ofNullable(stockQuoteDTO).orElseThrow(
                () -> new NullPointerException("RealTimeQuoteScheduled Error"));
        try {
          {
            RealTimeQuoteRepository.quote.put(stockQuote[0].getSymbol(),
                stockQuote[0]);
            this.counterIncrement();
          }
        } catch (NullPointerException ne) {
          System.out.println(ne);
          System.out.println(stockQuote[0].getSymbol() + "is no record");
        }
      } ;
    };

    Thread thread = new Thread(task);
    thread.start();
    try {
      thread.join();
    } catch (InterruptedException ie) {
      ie.printStackTrace();
    }

  }


  public void counterIncrement() {
    this.counter++;
  }



}
