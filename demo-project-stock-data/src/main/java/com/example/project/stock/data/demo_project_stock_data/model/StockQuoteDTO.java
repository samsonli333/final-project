package com.example.project.stock.data.demo_project_stock_data.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class StockQuoteDTO {
   private String symbol;
   private Double c;
   private Double d;
   private Double dp;
   private Double h;
   private Double l;
   private Double o;
   private Double pc;
   private Long t;
}
