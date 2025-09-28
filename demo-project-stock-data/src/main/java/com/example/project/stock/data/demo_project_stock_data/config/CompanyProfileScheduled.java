package com.example.project.stock.data.demo_project_stock_data.config;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import com.example.project.stock.data.demo_project_stock_data.model.ProfileDTO;
import com.example.project.stock.data.demo_project_stock_data.service.StockProfileService;
import com.example.project.stock.data.demo_project_stock_data.service.StockSymbolService;

@Component

public class CompanyProfileScheduled {

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private StockProfileService stockProfileService;

  @Autowired
  private StockSymbolService stockSymbolService;
  
  @Value("${limit}")
  private int limit;

  @Scheduled(cron = "0 0 * * * *")
  //@Scheduled(fixedDelay = 10000)
  public void runTask() {
    Runnable task = () -> {
      List<String> stockSymbol =
          this.stockSymbolService.findAllStockSymbol().stream().limit(this.limit)
              .map(x -> x.getSymbol()).collect(Collectors.toList());
      String symbolString =
          String.join(",", stockSymbol.toArray(new String[0]));

      // System.out.println(symbolString);

      String url = "http://localhost:8081/api/profile?symbol=" + symbolString;
      ProfileDTO[] profile = restTemplate.getForObject(url, ProfileDTO[].class);
      stockProfileService.setStockProfile(Arrays.asList(profile));

    };

    Thread thread = new Thread(task);
    thread.start();

    try {
      thread.join();
    } catch (InterruptedException ie) {
      ie.printStackTrace();
    }

  }

}
