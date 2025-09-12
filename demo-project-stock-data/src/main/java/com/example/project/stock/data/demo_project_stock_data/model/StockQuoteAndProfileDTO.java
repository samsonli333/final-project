package com.example.project.stock.data.demo_project_stock_data.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class StockQuoteAndProfileDTO {
   private Long stockId;
   private String symbol;
   private String sector;
   private String country;
   private String estimateCurrency;
   private String exchange;
   private String finnhubIndustry;
   private Double marketCapitalization;
   private String weburl;
   // private Double c;
   // private Double d;
   private Double value;
   // private Double h;
   // private Double l;
   // private Double o;
   // private Double pc;
   private LocalDateTime t;

}
