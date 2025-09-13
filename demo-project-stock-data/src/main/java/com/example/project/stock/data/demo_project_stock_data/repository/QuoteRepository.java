package com.example.project.stock.data.demo_project_stock_data.repository;

import java.util.HashMap;
import org.springframework.stereotype.Repository;

@Repository
public class QuoteRepository {
     public static final HashMap<String,Object> realTimeQuote = new HashMap<>();
}
