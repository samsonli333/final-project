package com.example.project.stock.data.demo_project_stock_data.lib;

import java.util.Comparator;
import com.example.project.stock.data.demo_project_stock_data.model.StockQuoteAndProfileDTO;


public class StockComparator implements Comparator<StockQuoteAndProfileDTO>{

  @Override
  public int compare(StockQuoteAndProfileDTO o1, StockQuoteAndProfileDTO o2) {
   if(o1.getMarketCapitalization() > o2.getMarketCapitalization()){
         return -1;
      }else if(o1.getMarketCapitalization() < o2.getMarketCapitalization()){
         return 1;
      }else{
         return 0;
      }
  }

  
}
