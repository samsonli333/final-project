package com.example.project.stock.data.demo_project_stock_data.repository;

import java.util.HashMap;
import org.springframework.stereotype.Repository;
import com.example.project.stock.data.demo_project_stock_data.model.StockQuoteDTO;
import lombok.Data;

@Repository
@Data
public class RealTimeQuoteRepository {
     public static final HashMap<String,StockQuoteDTO> realTimeQuote = new HashMap<>();
}
