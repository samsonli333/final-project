package com.example.project.stock.data.demo_project_stock_data.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DataQuote {
  private String name;
  private Double maxMarketCap;
  private List<GroupStockQuote> children;
}
