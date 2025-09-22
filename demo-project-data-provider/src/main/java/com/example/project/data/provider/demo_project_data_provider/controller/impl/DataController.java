package com.example.project.data.provider.demo_project_data_provider.controller.impl;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.example.project.data.provider.demo_project_data_provider.controller.DataOperation;
import com.example.project.data.provider.demo_project_data_provider.model.NewsDTO;
import com.example.project.data.provider.demo_project_data_provider.model.ProfileDTO;
import com.example.project.data.provider.demo_project_data_provider.model.StockQuoteDTO;
import com.example.project.data.provider.demo_project_data_provider.service.DataService;


@RestController
public class DataController implements DataOperation {

  @Autowired
  private DataService dataService;

  @Override
  public List<StockQuoteDTO> getData(String symbol) {
    return dataService.getData(symbol);
  }

  @Override
  public List<ProfileDTO> getProfile(String symbol) {
    return dataService.getProfile(symbol);
  }

  @Override
  public List<NewsDTO> getNews(String symbol){
    return dataService.getNews(symbol);
  }
}
