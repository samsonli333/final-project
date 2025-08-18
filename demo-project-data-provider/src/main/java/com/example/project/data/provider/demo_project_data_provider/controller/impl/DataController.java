package com.example.project.data.provider.demo_project_data_provider.controller.impl;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.example.project.data.provider.demo_project_data_provider.controller.DataOperation;
import com.example.project.data.provider.demo_project_data_provider.model.ProfileDTO;
import com.example.project.data.provider.demo_project_data_provider.model.QuoteDTO;


@RestController

public class DataController implements DataOperation {
  private String apiKey = "d2h0811r01qon4e9ss80d2h0811r01qon4e9ss8g";
  private String dataUrl =
      "https://finnhub.io/api/v1/quote?symbol=AAPL&token=" + this.apiKey;
  private String profileUrl =
      "https://finnhub.io/api/v1/stock/profile2?symbol=AAPL&token="
          + this.apiKey;

  @Autowired
  private RestTemplate restTemplate;

  @Override
  public String getData() {
    System.out.println("trigger");
    QuoteDTO[] quoteDto = restTemplate.getForObject(dataUrl, QuoteDTO[].class);
    return "good";
  }

  @Override
  public List<ProfileDTO> getProfile() {
    ProfileDTO[] profileDto =
        restTemplate.getForObject(profileUrl, ProfileDTO[].class);
    return Arrays.asList(profileDto);
  }
}
