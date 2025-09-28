package com.example.project.stock.data.demo_project_stock_data.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.project.stock.data.demo_project_stock_data.enity.StockProfile;
import com.example.project.stock.data.demo_project_stock_data.lib.DateConvert;
import com.example.project.stock.data.demo_project_stock_data.lib.StockComparator;
import com.example.project.stock.data.demo_project_stock_data.model.DataQuote;
import com.example.project.stock.data.demo_project_stock_data.model.GroupStockQuote;
import com.example.project.stock.data.demo_project_stock_data.model.StockQuoteAndProfileDTO;
import com.example.project.stock.data.demo_project_stock_data.model.StockQuoteDTO;
import com.example.project.stock.data.demo_project_stock_data.repository.StockProfileRepository;
import com.example.project.stock.data.demo_project_stock_data.repository.StockSymbolRepository;


@Service
public class StockQuoteService {

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private StockProfileRepository stockProfileRepository;

  @Autowired
  private StockSymbolRepository stockSymbolRepository;

  @Value("${limit}")
  private int limit;

  public DataQuote getRealTimeData() {

    List<StockQuoteAndProfileDTO> stockQuoteAndProfileDTO = new ArrayList<>();

    HashMap<String, GroupStockQuote> groupStockQuote = new HashMap<>();


    String[] symbolArr = (this.stockSymbolRepository
        .findAll(Sort.by(Sort.Direction.ASC, "symbol"))).stream()
            .limit(this.limit).map(x -> x.getSymbol())
            .collect(Collectors.toList()).toArray(new String[0]);

    String symbols = String.join(",", symbolArr);

    String url = "http://localhost:8081/api/data?symbol=" + symbols;

    StockQuoteDTO[] stockQuoteDTO =
        this.restTemplate.getForObject(url, StockQuoteDTO[].class);

    List<StockProfile> listStockProfile = this.stockProfileRepository.findAll();


    if (stockQuoteDTO != null) {
      stockQuoteAndProfileDTO = Arrays.asList(stockQuoteDTO).stream()
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

    Collections.sort(stockQuoteAndProfileDTO, new StockComparator());

    return new DataQuote("Info",
        stockQuoteAndProfileDTO.get(0).getMarketCapitalization(),
        List.copyOf(groupStockQuote.values()));

  }
}
