package com.example.project.stock.data.demo_project_stock_data.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;





@Controller
public class QuoteMapView {
  
  @GetMapping("/quote")  
  public String map(){
    return "quote-map";
  }

  @GetMapping("/history")
  public String chart(){
    return "history-chart";
  }
  
  @GetMapping("/quotedetails")
  public String quoteDetails(){
    return "quote-details";
  }
}
