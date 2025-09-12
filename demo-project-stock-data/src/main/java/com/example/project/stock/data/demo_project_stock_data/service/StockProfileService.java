package com.example.project.stock.data.demo_project_stock_data.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.project.stock.data.demo_project_stock_data.enity.StockSymbol;
import com.example.project.stock.data.demo_project_stock_data.enity.TempStockProfile;
import com.example.project.stock.data.demo_project_stock_data.model.ProfileDTO;
import com.example.project.stock.data.demo_project_stock_data.repository.StockProfileRepository;
import com.example.project.stock.data.demo_project_stock_data.repository.StockSymbolRepository;
import com.example.project.stock.data.demo_project_stock_data.repository.TempStockProfileRepository;

@Service
public class StockProfileService {

  @Autowired
  private TempStockProfileRepository tempStockProfileRepository;

  @Autowired
  private StockProfileRepository stockProfileRepository;

  @Autowired
  private StockSymbolRepository stockSymbolRepository;

  public boolean setStockProfile(List<ProfileDTO> profile) {
    int num = 0;
    try {
      List<TempStockProfile> stockProfile = profile.stream().map(x -> {

        StockSymbol stockSymbol =
            stockSymbolRepository.findBySymbol(x.getTicker())
                .orElseThrow(() -> new NullPointerException());

        return TempStockProfile.builder().country(x.getCountry())
            .estimateCurrency(x.getEstimateCurrency()).exchange(x.getExchange())
            .finnhubIndustry(x.getFinnhubIndustry())
            .marketCapitalization(x.getMarketCapitalization()).logo(x.getLogo())
            .name(x.getName()).phone(x.getPhone())
            .shareOutstanding(x.getShareOutstanding())
            .ipoDate(LocalDate.parse(x.getIpo())).weburl(x.getWeburl())
            .stockSymbol(stockSymbol).lastUpdateAt(LocalDateTime.now()).build();

      }).collect(Collectors.toList());

      if (stockProfile != null && stockProfile.size() > 0) {
        this.tempStockProfileRepository.saveAll(stockProfile);
        num = this.stockProfileRepository.upsert();
        return true;
      }
    } catch (NullPointerException ne) {
      System.out.println(ne);
    } finally {
      if (num > 0)
        this.tempStockProfileRepository.deleteAll();
    }
    return false;

  }

}
