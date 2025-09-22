package com.example.project.stock.data.demo_project_stock_data.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.project.stock.data.demo_project_stock_data.enity.StockProfile;
import com.example.project.stock.data.demo_project_stock_data.enity.StockSymbol;
import com.example.project.stock.data.demo_project_stock_data.lib.DateConvert;
import com.example.project.stock.data.demo_project_stock_data.model.DataQuote;
import com.example.project.stock.data.demo_project_stock_data.model.GroupStockQuote;
import com.example.project.stock.data.demo_project_stock_data.model.RealTimeQuoteDTO;
import com.example.project.stock.data.demo_project_stock_data.model.StockQuoteAndProfileDTO;
import com.example.project.stock.data.demo_project_stock_data.model.StockQuoteDTO;
import com.example.project.stock.data.demo_project_stock_data.repository.RealTimeQuoteRepository;
import com.example.project.stock.data.demo_project_stock_data.repository.StockProfileRepository;
import com.example.project.stock.data.demo_project_stock_data.repository.StockSymbolRepository;



@Service
public class RealTimeQuoteService {



  @Autowired
  private StockProfileRepository stockProfileRepository;


  @Autowired
  private StockSymbolRepository stockSymbolRepository;

  public DataQuote getRealTimeData() {

    List<StockQuoteAndProfileDTO> stockQuoteAndProfileDTO = new ArrayList<>();

    HashMap<String, GroupStockQuote> groupStockQuote = new HashMap<>();

   Collection<StockQuoteDTO> StockQuoteCollection = RealTimeQuoteRepository.quote.values();

    List<StockProfile> listStockProfile = this.stockProfileRepository.findAll();


    if (StockQuoteCollection != null) {
      stockQuoteAndProfileDTO = List.copyOf(StockQuoteCollection).stream()
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

    for (StockQuoteAndProfileDTO stock : stockQuoteAndProfileDTO) {
      if (groupStockQuote.containsKey(stock.getSector())) {
        groupStockQuote.get(stock.getSector()).getChildren().add(stock);
      } else {
        groupStockQuote.put(stock.getSector(), new GroupStockQuote(
            stock.getSector(), new ArrayList<>(Arrays.asList(stock))));
      }
    }
   
    return new DataQuote("Info",List.copyOf(groupStockQuote.values()));

  }



public RealTimeQuoteDTO getRealTimeBySymbol(String symbol){

  if( RealTimeQuoteRepository.quote.containsKey(symbol)){
     StockSymbol stockSymbol = this.stockSymbolRepository.findBySymbol(symbol).orElseThrow( () -> new NullPointerException());
   StockQuoteDTO stockQuoteDTO = RealTimeQuoteRepository.quote.get(symbol);
   StockProfile  stockProfile = this.stockProfileRepository.findByStockSymbol(stockSymbol);
    return new RealTimeQuoteDTO(stockQuoteDTO,stockProfile);
  }
  throw new NullPointerException("no value");
}

}
