package com.example.project.stock.data.demo_project_stock_data.model;

import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OhlcvHistory {
     private List<Double> close;
     private List<Double> low;
     private List<Double> open;
     private List<Double> high;
     private List<LocalDate> date;
}
