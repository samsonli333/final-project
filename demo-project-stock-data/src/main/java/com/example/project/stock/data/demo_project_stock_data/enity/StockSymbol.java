package com.example.project.stock.data.demo_project_stock_data.enity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(indexes = {
  @Index(name = "idx_symbol",columnList = "symbol",unique = true)
})
public class StockSymbol {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "symbol",unique = true)
  private String symbol;
  private String sector;
  private LocalDate founded;

  @Column(name ="updated_at")
  private LocalDateTime updatedAt;
}
