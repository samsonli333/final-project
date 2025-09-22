package com.example.project.stock.data.demo_project_stock_data.repository;


import java.util.HashMap;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;
import com.example.project.stock.data.demo_project_stock_data.model.StockQuoteDTO;

@Repository
public class RealTimeQuoteRepository {
     public final static HashMap<String,StockQuoteDTO> quote = new HashMap<>();


     public static String[] getSymbols(){
          return quote.values().stream().map( x -> x.getSymbol())
          .collect(Collectors.toList()).toArray(new String[0]);
     } 
}
