package com.example.project.stock.data.demo_project_stock_data.model;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;




@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GroupStockQuote {
    private String sector;
    private List<StockQuoteAndProfileDTO> children;

}
