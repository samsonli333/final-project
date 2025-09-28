package com.example.project.stock.data.demo_project_stock_data.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.project.stock.data.demo_project_stock_data.enity.StockProfile;
import com.example.project.stock.data.demo_project_stock_data.lib.DateConvert;
import com.example.project.stock.data.demo_project_stock_data.model.StockQuoteAndProfileDTO;
import com.example.project.stock.data.demo_project_stock_data.model.StockQuoteDTO;
import com.example.project.stock.data.demo_project_stock_data.repository.RealTimeQuoteRepository;
import com.example.project.stock.data.demo_project_stock_data.repository.StockProfileRepository;




@Service
public class MarqueeService {



  @Autowired
  private StockProfileRepository stockProfileRepository;


  public List<StockQuoteAndProfileDTO> getMarquee() {

    List<StockQuoteAndProfileDTO> stockQuoteAndProfileDTO = new ArrayList<>();

    Collection<StockQuoteDTO> StockQuoteCollection =
        RealTimeQuoteRepository.quote.values();

    List<StockProfile> listStockProfile = this.stockProfileRepository.findAll();

    if (StockQuoteCollection != null) {
      return stockQuoteAndProfileDTO = List.copyOf(StockQuoteCollection).stream()
          .filter(x -> (listStockProfile.stream().filter(
              item -> item.getStockSymbol().getSymbol().equals(x.getSymbol()))
              .collect(Collectors.toList())).size() > 0)
          .map(x -> {

            StockProfile stockProfile = (listStockProfile.stream().filter(
                item -> item.getStockSymbol().getSymbol().equals(x.getSymbol()))
                .collect(Collectors.toList())).get(0);

            return StockQuoteAndProfileDTO.builder()
                .stockId(stockProfile.getStockSymbol().getId())
                .symbol(x.getSymbol())
                .sector(stockProfile.getStockSymbol().getSector())
                .country(stockProfile.getCountry())
                .estimateCurrency(stockProfile.getEstimateCurrency())
                .exchange(stockProfile.getExchange())
                .finnhubIndustry(stockProfile.getFinnhubIndustry())
                .marketCapitalization(stockProfile.getMarketCapitalization())
                .weburl(stockProfile.getWeburl()).value(x.getDp())
                .t(DateConvert.convertDate(x.getT())).build();
          }).collect(Collectors.toList());      
    }

    return stockQuoteAndProfileDTO;
    

  }



}

