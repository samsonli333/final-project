package com.example.project.stock.data.demo_project_stock_data.model;

import com.example.project.stock.data.demo_project_stock_data.enity.StockProfile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RealTimeQuoteDTO {
     private StockQuoteDTO stockQuoteDTO;
     private StockProfile  stockProfile;
}
