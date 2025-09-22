package com.example.project.data.provider.demo_project_data_provider.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.project.data.provider.demo_project_data_provider.model.NewsDTO;
import com.example.project.data.provider.demo_project_data_provider.model.ProfileDTO;
import com.example.project.data.provider.demo_project_data_provider.model.StockQuoteDTO;


@RequestMapping(value="/api")
public interface DataOperation {

  @GetMapping(value="/data")
  List<StockQuoteDTO> getData(@RequestParam(name="symbol") String symbol);

  @GetMapping(value = "/profile")
  List<ProfileDTO> getProfile(@RequestParam(name ="symbol") String symbol);

  @GetMapping(value = "/news")
  List<NewsDTO>  getNews(@RequestParam(name ="symbol") String symbol);
} 
