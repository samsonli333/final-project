package com.example.project.stock.data.demo_project_stock_data.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.project.stock.data.demo_project_stock_data.model.NewsDTO;

@Service
public class NewsService {

  @Autowired
  private RestTemplate restTemplate;
  
  public List<NewsDTO> getNews(String symbol){
    String url = "http://localhost:8081/api/news?symbol=" + symbol;

    NewsDTO[] newsDtoRes = this.restTemplate.getForObject(url,NewsDTO[].class);
    NewsDTO[] newsDto = Optional.ofNullable(newsDtoRes).orElseThrow(() -> new NullPointerException());

    return Arrays.asList(newsDto);
  }
}
