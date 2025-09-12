package com.example.project.data.provider.demo_project_data_provider.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.project.data.provider.demo_project_data_provider.model.ProfileDTO;
import com.example.project.data.provider.demo_project_data_provider.model.QuoteDTO;
import com.example.project.data.provider.demo_project_data_provider.model.StockQuoteDTO;


@Service
public class DataService {

  @Value("${api-key}")
  private String apiKey;

  private String dataUrl = "https://finnhub.io/api/v1/quote?symbol=";
  private String profileUrl =
      "https://finnhub.io/api/v1/stock/profile2?symbol=";


  @Autowired
  private RestTemplate restTemplate;


  public List<StockQuoteDTO> getData(String symbol) {
    List<StockQuoteDTO> listQuote = new ArrayList<>();
    String[] arrSymbol = symbol.split(",");
    String token = "&token=" + this.apiKey;

    for (String str : arrSymbol) {
      System.out.println("str: " + str);
      String url = this.dataUrl + str + token;
      QuoteDTO quoteDto = restTemplate.getForObject(url, QuoteDTO.class);


      if (quoteDto != null) {
        StockQuoteDTO stockQuoteDto = StockQuoteDTO.builder().c(quoteDto.getC())
            .d(quoteDto.getD()).dp(quoteDto.getDp()).h(quoteDto.getH())
            .l(quoteDto.getL()).o(quoteDto.getO()).pc(quoteDto.getPc())
            .t(quoteDto.getT()).symbol(str).build();
        listQuote.add(stockQuoteDto);
      }

    }

    return listQuote;
  }

  public List<ProfileDTO> getProfile(String symbol) {
    List<ProfileDTO> listProfile = new ArrayList<>();
    String[] arrSymbol = symbol.split(",");
    String token = "&token=" + this.apiKey;

    for (String str : arrSymbol) {
      String url = this.profileUrl + str + token;
      ProfileDTO profileDto = restTemplate.getForObject(url, ProfileDTO.class);
      listProfile.add(profileDto);
    }

    return listProfile;
  }

}
